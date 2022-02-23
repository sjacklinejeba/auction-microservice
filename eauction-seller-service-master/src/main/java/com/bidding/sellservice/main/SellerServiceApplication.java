package com.bidding.sellservice.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.bidding")
@EntityScan("com.bidding")
public class SellerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellerServiceApplication.class, args);
	}

}
