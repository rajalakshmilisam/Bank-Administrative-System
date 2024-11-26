package com.demo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.demo.exception.BankException;
import com.demo.exception.BankNotFoundException;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
@ComponentScan("com.demo") // This is a spring core
@EntityScan(basePackages = { "com.demo.entity" })
@EnableJpaRepositories("com.demo.dao")
public class ClaimManagementApplication extends SpringBootServletInitializer{

	public static void main(String[] args) throws BankException, BankNotFoundException {
		ApplicationContext ctx = SpringApplication.run(ClaimManagementApplication.class, args);
		System.out.println("Welcome to Spring Boot Applications");

	}

}
