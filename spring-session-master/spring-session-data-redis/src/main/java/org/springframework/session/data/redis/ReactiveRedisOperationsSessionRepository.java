/*
 * Copyright 2014-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.session.data.redis;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import reactor.core.publisher.Mono;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.session.MapSession;
import org.springframework.session.ReactorSessionRepository;
import org.springframework.session.Session;
import org.springframework.util.Assert;

/**
 * A {@link ReactorSessionRepository} that is implemented using Spring Data's
 * {@link ReactiveRedisOperations}.
 *
 * @author Vedran Pavic
 * @since 2.0
 */
public class ReactiveRedisOperationsSessionRepository implements
		ReactorSessionRepository<ReactiveRedisOperationsSessionRepository.RedisSession> {

	/**
	 * The default prefix for each key and channel in Redis used by Spring Session.
	 */
	static final String DEFAULT_SPRING_SESSION_REDIS_PREFIX = "spring:session:";

	/**
	 * The key in the Hash representing {@link Session#getCreationTime()}.
	 */
	static final String CREATION_TIME_KEY = "creationTime";

	/**
	 * The key in the Hash representing {@link Session#getLastAccessedTime()}.
	 */
	static final String LAST_ACCESSED_TIME_KEY = "lastAccessedTime";

	/**
	 * The key in the Hash representing {@link Session#getMaxInactiveInterval()} .
	 */
	static final String MAX_INACTIVE_INTERVAL_KEY = "maxInactiveInterval";

	/**
	 * The prefix of the key for used for session attributes. The suffix is the name of
	 * the session attribute. For example, if the session contained an attribute named
	 * attributeName, then there would be an entry in the hash named
	 * sessionAttr:attributeName that mapped to its value.
	 */
	static final String ATTRIBUTE_PREFIX = "attribute:";

	private final ReactiveRedisOperations<String, Object> sessionRedisOperations;

	/**
	 * The prefix for every key used by Spring Session in Redis.
	 */
	private String keyPrefix = DEFAULT_SPRING_SESSION_REDIS_PREFIX;

	/**
	 * If non-null, this value is used to override the default value for
	 * {@link RedisSession#setMaxInactiveInterval(Duration)}.
	 */
	private Integer defaultMaxInactiveInterval;

	private RedisFlushMode redisFlushMode = RedisFlushMode.ON_SAVE;

	public ReactiveRedisOperationsSessionRepository(
			ReactiveRedisOperations<String, Object> sessionRedisOperations) {
		Assert.notNull(sessionRedisOperations, "sessionRedisOperations cannot be null");
		this.sessionRedisOperations = sessionRedisOperations;
	}

	public void setRedisKeyNamespace(String namespace) {
		Assert.hasText(namespace, "namespace cannot be null or empty");
		this.keyPrefix = DEFAULT_SPRING_SESSION_REDIS_PREFIX + namespace.trim() + ":";
	}

	/**
	 * Sets the maximum inactive interval in seconds between requests before newly created
	 * sessions will be invalidated. A negative time indicates that the session will never
	 * timeout. The default is 1800 (30 minutes).
	 *
	 * @param defaultMaxInactiveInterval the number of seconds that the {@link Session}
	 * should be kept alive between client requests.
	 */
	public void setDefaultMaxInactiveInterval(int defaultMaxInactiveInterval) {
		this.defaultMaxInactiveInterval = defaultMaxInactiveInterval;
	}

	/**
	 * Sets the redis flush mode. Default flush mode is {@link RedisFlushMode#ON_SAVE}.
	 *
	 * @param redisFlushMode the new redis flush mode
	 */
	public void setRedisFlushMode(RedisFlushMode redisFlushMode) {
		Assert.notNull(redisFlushMode, "redisFlushMode cannot be null");
		this.redisFlushMode = redisFlushMode;
	}

	@Override
	public Mono<RedisSession> createSession() {
		return Mono.defer(() -> {
			RedisSession session = new RedisSession();

			if (this.defaultMaxInactiveInterval != null) {
				session.setMaxInactiveInterval(
						Duration.ofSeconds(this.defaultMaxInactiveInterval));
			}

			return Mono.just(session);
		});
	}

	@Override
	public Mono<Void> save(RedisSession session) {
		return session.saveDelta().and(s -> {
			if (session.isNew) {
				session.setNew(false);
			}

			s.onComplete();
		});
	}

	@Override
	public Mono<RedisSession> findById(String id) {
		String sessionKey = getSessionKey(id);

		return this.sessionRedisOperations.opsForHash().entries(sessionKey)
				.collect(
						Collectors.toMap(e -> e.getKey().toString(), Map.Entry::getValue))
				.filter(map -> !map.isEmpty()).map(new SessionMapper(id))
				.filter(session -> !session.isExpired()).map(RedisSession::new)
				.switchIfEmpty(Mono.defer(() -> deleteById(id).then(Mono.empty())));
	}

	@Override
	public Mono<Void> deleteById(String id) {
		String sessionKey = getSessionKey(id);

		return this.sessionRedisOperations.delete(sessionKey).then();
	}

	private static String getAttributeKey(String attributeName) {
		return ATTRIBUTE_PREFIX + attributeName;
	}

	private String getSessionKey(String sessionId) {
		return this.keyPrefix + "sessions:" + sessionId;
	}

	/**
	 * A custom implementation of {@link Session} that uses a {@link MapSession} as the
	 * basis for its mapping. It keeps track of any attributes that have changed. When
	 * {@link RedisSession#saveDelta()} is invoked all the attributes that have been
	 * changed will be persisted.
	 */
	final class RedisSession implements Session {

		private final MapSession cached;

		private final Map<String, Object> delta = new HashMap<>();

		private boolean isNew;

		private String originalSessionId;

		/**
		 * Creates a new instance ensuring to mark all of the new attributes to be
		 * persisted in the next save operation.
		 */
		RedisSession() {
			this(new MapSession());
			this.delta.put(CREATION_TIME_KEY, getCreationTime().toEpochMilli());
			this.delta.put(MAX_INACTIVE_INTERVAL_KEY,
					(int) getMaxInactiveInterval().getSeconds());
			this.delta.put(LAST_ACCESSED_TIME_KEY, getLastAccessedTime().toEpochMilli());
			this.isNew = true;
			this.flushImmediateIfNecessary();
		}

		/**
		 * Creates a new instance from the provided {@link MapSession}.
		 *
		 * @param mapSession the {@link MapSession} that represents the persisted session
		 * that was retrieved. Cannot be null.
		 */
		RedisSession(MapSession mapSession) {
			Assert.notNull(mapSession, "mapSession cannot be null");
			this.cached = mapSession;
			this.originalSessionId = mapSession.getId();
		}

		public String getId() {
			return this.cached.getId();
		}

		public String changeSessionId() {
			return this.cached.changeSessionId();
		}

		public <T> T getAttribute(String attributeName) {
			return this.cached.getAttribute(attributeName);
		}

		public Set<String> getAttributeNames() {
			return this.cached.getAttributeNames();
		}

		public void setAttribute(String attributeName, Object attributeValue) {
			this.cached.setAttribute(attributeName, attributeValue);
			putAndFlush(getAttributeKey(attributeName), attributeValue);
		}

		public void removeAttribute(String attributeName) {
			this.cached.removeAttribute(attributeName);
			putAndFlush(getAttributeKey(attributeName), null);
		}

		public Instant getCreationTime() {
			return this.cached.getCreationTime();
		}

		public void setLastAccessedTime(Instant lastAccessedTime) {
			this.cached.setLastAccessedTime(lastAccessedTime);
			putAndFlush(LAST_ACCESSED_TIME_KEY, getLastAccessedTime().toEpochMilli());
		}

		public Instant getLastAccessedTime() {
			return this.cached.getLastAccessedTime();
		}

		public void setMaxInactiveInterval(Duration interval) {
			this.cached.setMaxInactiveInterval(interval);
			putAndFlush(MAX_INACTIVE_INTERVAL_KEY,
					(int) getMaxInactiveInterval().getSeconds());
		}

		public Duration getMaxInactiveInterval() {
			return this.cached.getMaxInactiveInterval();
		}

		public boolean isExpired() {
			return this.cached.isExpired();
		}

		public void setNew(boolean isNew) {
			this.isNew = isNew;
		}

		public boolean isNew() {
			return this.isNew;
		}

		private void flushImmediateIfNecessary() {
			if (ReactiveRedisOperationsSessionRepository.this.redisFlushMode == RedisFlushMode.IMMEDIATE) {
				saveDelta();
			}
		}

		private void putAndFlush(String a, Object v) {
			this.delta.put(a, v);
			flushImmediateIfNecessary();
		}

		private Mono<Void> saveDelta() {
			String sessionId = getId();
			Mono<Void> changeSessionId = saveChangeSessionId(sessionId);

			if (this.delta.isEmpty()) {
				return changeSessionId.and(Mono.empty());
			}

			String sessionKey = getSessionKey(sessionId);

			Mono<Boolean> update = ReactiveRedisOperationsSessionRepository.this.sessionRedisOperations
					.opsForHash().putAll(sessionKey, this.delta);

			Mono<Boolean> setTtl = ReactiveRedisOperationsSessionRepository.this.sessionRedisOperations
					.expire(sessionKey, getMaxInactiveInterval());

			return changeSessionId.and(update).and(setTtl).and(s -> {
				this.delta.clear();
				s.onComplete();
			}).then();
		}

		private Mono<Void> saveChangeSessionId(String sessionId) {
			if (isNew() || sessionId.equals(this.originalSessionId)) {
				return Mono.empty();
			}

			String originalSessionKey = getSessionKey(this.originalSessionId);
			String sessionKey = getSessionKey(sessionId);

			return ReactiveRedisOperationsSessionRepository.this.sessionRedisOperations
					.rename(originalSessionKey, sessionKey).and(s -> {
						this.originalSessionId = sessionId;
						s.onComplete();
					});
		}

	}

	private static final class SessionMapper
			implements Function<Map<String, Object>, MapSession> {

		private final String id;

		private SessionMapper(String id) {
			this.id = id;
		}

		@Override
		public MapSession apply(Map<String, Object> map) {
			MapSession session = new MapSession(this.id);

			session.setCreationTime(
					Instant.ofEpochMilli((long) map.get(CREATION_TIME_KEY)));
			session.setLastAccessedTime(
					Instant.ofEpochMilli((long) map.get(LAST_ACCESSED_TIME_KEY)));
			session.setMaxInactiveInterval(
					Duration.ofSeconds((int) map.get(MAX_INACTIVE_INTERVAL_KEY)));

			map.forEach((name, value) -> {
				if (name.startsWith(ATTRIBUTE_PREFIX)) {
					session.setAttribute(name.substring(ATTRIBUTE_PREFIX.length()),
							value);
				}
			});

			return session;
		}

	}

}
