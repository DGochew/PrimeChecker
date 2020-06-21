package com.test.primechecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST Prime Checker",
                "Create a Java (8 or greater) REST microservice which allows us to:\n" +
                        "o Find out if a given number is a prime number\n" +
                        "o Find out the next prime after a given number (i.e. Input: N, output: P where P >=N and P is prime)",
                "1",
                "https://github.com/DGochew/PrimeChecker",
                new Contact("Dimitar Gochev", "https://github.com/DGochew/PrimeChecker", "dimitar.k.gochev@gmail.com"),
                "GitHub", "https://github.com/DGochew/PrimeChecker", Collections.emptyList());
    }
}
