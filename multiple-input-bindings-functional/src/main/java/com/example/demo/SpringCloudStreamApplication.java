package com.example.demo;

import java.util.function.Supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient;

@SpringBootApplication
@EnableSchemaRegistryClient
public class SpringCloudStreamApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(SpringCloudStreamApplication.class, args);
	}
	

}
