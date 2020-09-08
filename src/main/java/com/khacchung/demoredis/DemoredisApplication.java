package com.khacchung.demoredis;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EntityScan(basePackages = "com.khacchung.demoredis")
public class DemoredisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoredisApplication.class, args);
		BasicConfigurator.configure();
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
