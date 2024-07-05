package com.integrador.msproducts;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.function.Function;

@EnableDiscoveryClient
@SpringBootApplication
public class MsProductosApplication {


	public static void main(String[] args) {
		// Verificar si estamos en Render
		boolean isRender = System.getenv("RENDER") != null;

		if (!isRender) {
			// Si no estamos en Render, cargamos el .env como antes
			Dotenv dotenv = Dotenv.load();
			setEnvironmentVariables((Function<String, String>) dotenv);
		} else {
			// Si estamos en Render, usamos las variables de entorno del sistema
			setEnvironmentVariables(System::getenv);
		}

		SpringApplication.run(MsProductosApplication.class, args);
	}

	private static void setEnvironmentVariables(Function<String, String> getEnv) {
		// Configuración de la base de datos
		System.setProperty("spring.datasource.url", getEnv.apply("DB_URL"));
		System.setProperty("spring.datasource.username", getEnv.apply("DB_USERNAME"));
		System.setProperty("spring.datasource.password", getEnv.apply("DB_PASSWORD"));

		System.setProperty("eureka.client.serviceUrl.defaultZone", getEnv.apply("EUREKA_URL"));

		// Configuración de Cloudinary
		System.setProperty("cloudinary.cloud_name", getEnv.apply("CLOUDINARY_CLOUD_NAME"));
		System.setProperty("cloudinary.api_key", getEnv.apply("CLOUDINARY_API_KEY"));
		System.setProperty("cloudinary.api_secret", getEnv.apply("CLOUDINARY_API_SECRET"));
	}
}
