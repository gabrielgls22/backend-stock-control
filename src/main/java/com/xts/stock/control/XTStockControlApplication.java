package com.xts.stock.control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableMongoRepositories
@ComponentScan(basePackages = "com.xts.stock.control")
public class XTStockControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(XTStockControlApplication.class, args);
	}

}
