package com.business;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BusinessApplication {
	private static Logger logger = LoggerFactory.getLogger(BusinessApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(BusinessApplication.class, args);
	}
}
