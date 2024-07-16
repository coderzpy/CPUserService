package com.example.cpuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.validation.annotation.Validated;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableJpaAuditing
@Validated
public class CpUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CpUserServiceApplication.class, args);
	}

}
