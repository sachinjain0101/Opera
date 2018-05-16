package com.bullhorn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
public class OperaDataMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperaDataMapperApplication.class, args);
	}
	

}
