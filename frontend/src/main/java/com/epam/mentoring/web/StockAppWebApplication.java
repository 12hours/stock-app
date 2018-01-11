package com.epam.mentoring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockAppWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockAppWebApplication.class, args);
        System.out.println("debug");

	}
}
