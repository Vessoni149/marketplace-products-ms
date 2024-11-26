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

		SpringApplication.run(MsProductosApplication.class, args);
	}

}
