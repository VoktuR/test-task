package com.example.rav.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RavApplication {

	public static void main(String[] args) {
		SpringApplication.run(RavApplication.class, args);
	}

}
