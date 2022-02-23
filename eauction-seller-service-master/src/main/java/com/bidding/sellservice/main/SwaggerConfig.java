package com.bidding.sellservice.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.sellservice.controller")).paths(PathSelectors.any())
				.build();
	}

	@Bean
	ApiInfo apiInfo() {
		final ApiInfoBuilder builder = new ApiInfoBuilder();
		builder.title("Spring Boot API").version("1.0").license("(C) Copyright")
				.description("List of all endpoints used in API");
		return builder.build();
	}

}