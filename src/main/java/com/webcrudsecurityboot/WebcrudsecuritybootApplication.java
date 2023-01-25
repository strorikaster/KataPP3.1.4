package com.webcrudsecurityboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EntityScan(basePackages = {"com.webcrudsecurityboot.model"})
@SpringBootApplication
public class WebcrudsecuritybootApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebcrudsecuritybootApplication.class, args);
	}

}
