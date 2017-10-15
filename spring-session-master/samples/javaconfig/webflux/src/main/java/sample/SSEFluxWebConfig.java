/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package sample;

import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSEFluxWebConfig {

	// TODO * Once there is a Spring Boot starter for thymeleaf-spring5, there would be no
	// need to have
	// TODO that @EnableConfigurationProperties annotation or use it for declaring the
	// beans down in the
	// TODO "thymeleaf" section below.

	private ApplicationContext applicationContext;

	public SSEFluxWebConfig(final ApplicationContext applicationContext) {
		super();
		this.applicationContext = applicationContext;
	}

	/*
	 * -------------------------------------- THYMELEAF CONFIGURATION
	 * --------------------------------------
	 */

	// TODO * If there was a Spring Boot starter for thymeleaf-spring5 most probably some
	// or all of these
	// TODO resolver and engine beans would not need to be specifically declared here.

	@Bean
	public SpringResourceTemplateResolver thymeleafTemplateResolver() {

		final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(this.applicationContext);
		resolver.setPrefix("classpath:/templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCacheable(false);
		resolver.setCheckExistence(true);
		return resolver;

	}

	@Bean
	public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
		// We override here the SpringTemplateEngine instance that would otherwise be
		// instantiated by
		// Spring Boot because we want to apply the SpringWebFlux-specific context
		// factory, link builder...
		final SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
		templateEngine.setTemplateResolver(thymeleafTemplateResolver());
		return templateEngine;
	}

	@Bean
	public ThymeleafReactiveViewResolver thymeleafChunkedAndDataDrivenViewResolver() {
		final ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
		viewResolver.setTemplateEngine(thymeleafTemplateEngine());
		viewResolver.setOrder(1);
		viewResolver.setResponseMaxChunkSizeBytes(8192); // OUTPUT BUFFER size limit
		return viewResolver;
	}

}
