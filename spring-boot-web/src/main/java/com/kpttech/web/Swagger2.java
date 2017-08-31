package com.kpttech.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.kpttech.web.controller"))
				// .paths(PathSelectors.any())
				.paths(Predicates.or(
						// 这里添加你需要展示的接口
						PathSelectors.ant("/user/**"), PathSelectors.ant("/wx/**")))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("springboot利用swagger构建api文档")
				.description("简单优雅的restfun风格，http://blog.csdn.net/gjq246")
				.termsOfServiceUrl("http://blog.csdn.net/gjq246").version("1.0").build();
	}

}
