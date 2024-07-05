package com.integrador.msproducts;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsProductosApplication {


	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		// Configuración de la base de datos
		System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
		System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
		System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));

		System.setProperty("eureka.client.serviceUrl.defaultZone", dotenv.get("EUREKA_URL"));

		// Configuración de Cloudinary
		System.setProperty("cloudinary.cloud_name", dotenv.get("CLOUDINARY_CLOUD_NAME"));
		System.setProperty("cloudinary.api_key", dotenv.get("CLOUDINARY_API_KEY"));
		System.setProperty("cloudinary.api_secret", dotenv.get("CLOUDINARY_API_SECRET"));

		SpringApplication.run(MsProductosApplication.class, args);
	}
}
