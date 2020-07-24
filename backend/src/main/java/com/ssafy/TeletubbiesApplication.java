package com.ssafy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TeletubbiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeletubbiesApplication.class, args);
	}

}
