package com.example.RealEstateSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealEstateSiteApplication {


	public static void main(String[] args) {
		DatabaseCreation.createDatabase();
		SpringApplication.run(RealEstateSiteApplication.class, args);
	}

}
