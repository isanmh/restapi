package com.example.restservice.restapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	// model mapper instalasi DTO ke entity
	@Bean // bean adalah anotasi untuk membuat object injection
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
