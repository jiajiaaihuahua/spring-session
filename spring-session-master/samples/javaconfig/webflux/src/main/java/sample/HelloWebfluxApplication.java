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

package sample;

import reactor.ipc.netty.NettyContext;
import reactor.ipc.netty.http.server.HttpServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

/**
 * @author Rob Winch
 * @since 5.0
 */
@Configuration
@EnableWebFlux
@ComponentScan
public class HelloWebfluxApplication {
	@Value("${server.port:8080}")
	private int port = 8080;

	public static void main(String[] args) throws Exception {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				HelloWebfluxApplication.class)) {
			context.getBean(NettyContext.class).onClose().block();
		}
	}

	@Bean
	public NettyContext nettyContext(ApplicationContext context) {
		HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
		HttpServer httpServer = HttpServer.create("localhost", this.port);
		return httpServer.newHandler(adapter).block();
	}
}
