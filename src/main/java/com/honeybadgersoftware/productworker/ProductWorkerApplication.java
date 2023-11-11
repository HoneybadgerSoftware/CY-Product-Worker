package com.honeybadgersoftware.productworker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ProductWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductWorkerApplication.class, args);
	}

}
