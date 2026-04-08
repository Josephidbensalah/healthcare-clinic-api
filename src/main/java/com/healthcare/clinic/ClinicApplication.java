package com.healthcare.clinic;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
@OpenAPIDefinition(
		info = @Info(
				title = "Clinic Healthcare REST API Documentation",
				description = "Clinic REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Youssef  ID-BENSALAH",
						email = "youssefidbensaleh@gmail.com",
						url = "https://github.com/josephidbensalah"),
				license = @License(name = "Apache 2.0",url = "https://github.com/josephidbensalah")
		),
		externalDocs = @ExternalDocumentation(
				description = "Clinic Healthcare REST API Documentation",
				url = "https://github.com/josephidbensalah"
		)
)
public class ClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}

}
