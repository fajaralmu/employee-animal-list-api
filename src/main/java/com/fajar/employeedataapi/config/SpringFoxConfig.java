package com.fajar.employeedataapi.config;
  

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 
@Configuration
public class SpringFoxConfig {                                    
 
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
				.apiInfo(apiInfo()).select()
				.paths(PathSelectors.any())   
//				.paths(postPaths())
				.build();
	}
 
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Employee and Breed API")
				.description("Test API")
				.contact(c())
				.version("1.0").build();
	}

	private Contact c() {
		Contact c = new Contact("Fajar", "github.com/fajaralmu",  "somabangsa@gmail.com"); 
		return c;
	}
}