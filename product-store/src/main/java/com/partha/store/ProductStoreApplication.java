package com.partha.store;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
//@EnableSwagger2
@EnableCaching
public class ProductStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductStoreApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplateInstance()
	{
		return new RestTemplate();
	}
	
}
