package com.luisangel.jwtapi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity

public class JwtapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtapiApplication.class, args);
	}

}
