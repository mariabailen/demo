package com.mariabailen.simfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ServletComponentScan
@SpringBootApplication
@EnableJpaRepositories("com.maria.simfood.repository") 
@EntityScan("com.example.simfood.model")
public class SimFoodApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SimFoodApplication.class, args);
	}

}
