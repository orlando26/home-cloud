package com.orlando.quesadillacloud;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class QuesadillacloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuesadillacloudApplication.class, args);
	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("quesadilla-home-apis")
				.select()
				.paths(PathSelectors.regex("/cloud.*")) 
				.build()
				.apiInfo(getApiInformation());
	}

	private ApiInfo getApiInformation(){
        return new ApiInfo("Cloud API Documentation",
                "Cubing-mty rest service backend project",
                "1.0",
                "API Terms of Service URL",
                new Contact("Orlando Loza Castro", "orlando26.github.io", "orlandolozaarr@gmail.com"),
                "API License",
                "API License URL",
                Collections.emptyList()
                );
    }

}
