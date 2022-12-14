package com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}
	
	@Bean
	ObjectMapper objectMapper() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.registerModule(new JavaTimeModule());
		
		return objectMapper;
	}
}
