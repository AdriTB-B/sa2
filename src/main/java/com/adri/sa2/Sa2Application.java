package com.adri.sa2;

import com.adri.sa2.application.storage.StorageConfigurationProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
@EnableConfigurationProperties(StorageConfigurationProperties.class)
public class Sa2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sa2Application.class, args);
	}

	@Bean
	CommandLineRunner start(StorageConfigurationProperties properties){
		return (args) -> {
			if(args.length > 0){
				properties.setPath(args[0]);
			}
		};
	}
}
