package com.example.dailyNotesAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DailyNotesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailyNotesApiApplication.class, args);
	}

}
