package com.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.springapp.configuration.JpaConfiguration;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.springapp"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class HealthCare {

	public static void main(String[] args) {
		SpringApplication.run(HealthCare.class, args);
	}
}
