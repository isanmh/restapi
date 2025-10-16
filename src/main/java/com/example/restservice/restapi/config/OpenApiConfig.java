package com.example.restservice.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("RestServices API")
                                .version("1.0.0")
                                .description("API Documentation for Restservice")
                                .contact(new Contact()
                                        .name("Ihsan MH").email("inix@gmail.com"))
                                .license(new License().name("MIT")
                                        .url("https://opensource.org/licenses/MIT")));
    }
}
