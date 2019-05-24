package com.itcpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableJpaRepositories
@SpringBootApplication
public class SimpleControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleControllerApplication.class, args);
	}

}
