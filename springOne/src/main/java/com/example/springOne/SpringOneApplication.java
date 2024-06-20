package com.example.springOne;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.springOne")
public class SpringOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOneApplication.class, args);
	}

}
