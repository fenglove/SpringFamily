package com.example.complexcontrollerdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@EnableJpaRepositories
@SpringBootApplication
public class ComplexControllerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplexControllerDemoApplication.class, args);
	}

}
