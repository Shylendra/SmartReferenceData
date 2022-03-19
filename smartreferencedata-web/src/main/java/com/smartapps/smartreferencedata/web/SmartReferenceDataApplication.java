package com.smartapps.smartreferencedata.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableCaching
@SpringBootApplication
public class SmartReferenceDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartReferenceDataApplication.class, args);
	}

}
