package com.musinsa.task.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {

		Info info = new Info()
			.title("musinsa task")
			.version("1.0.0")
			.description("musinsa task API");

		return new OpenAPI()
			.info(info);
	}
}
