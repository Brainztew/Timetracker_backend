package com.timetracker_backend.timetracker_backend;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimetrackerBackendApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("Europe/Stockholm"));
		SpringApplication.run(TimetrackerBackendApplication.class, args);
	}

}
