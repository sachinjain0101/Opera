package com.bullhorn;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OperaDockertestApplication {

	public static void main(String[] args) {

		SpringApplication.run(OperaDockertestApplication.class, args);
	}
	
	@Bean
	public String hi() {
		File f = new File("app.properties");
		if (f.exists()) {
			System.out.println("*************************"+f.getAbsolutePath());
		} else
			System.out.println("*************************not found");
		return "";
	}
	
}
