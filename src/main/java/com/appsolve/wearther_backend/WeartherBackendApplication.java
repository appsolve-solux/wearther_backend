package com.appsolve.wearther_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class WeartherBackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(WeartherBackendApplication.class, args);
	}

}
