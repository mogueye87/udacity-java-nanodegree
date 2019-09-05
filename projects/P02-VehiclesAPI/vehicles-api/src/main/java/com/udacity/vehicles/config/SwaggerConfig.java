package com.udacity.vehicles.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ApiResponses(value = {
		@ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation for the proper request"),
		@ApiResponse(code = 401, message = "Due to security constraints, you access request can not be authorized"),
		@ApiResponse(code = 500, message = "The server is down. Please make sure the location of the microservice is running") })
public class SwaggerConfig {
	List<VendorExtension> vendorExtensions = new ArrayList<>();
	Contact contact = new Contact("Mouhamadou GUEYE", "www.udacity.com", "mogueye87@gmail.com");
	ApiInfo apiInfo = new ApiInfo("Vehicle RESTful Web Service documentation",
			"This pages documents Vehicle RESTful Web Service endpoints", "1.0",
			"www.udacity.com", contact, "Udacity 2.0",
			"License", vendorExtensions);

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo).select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false);
	}
}
