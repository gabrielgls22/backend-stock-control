package com.xts.stock.control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class XTStockControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(XTStockControlApplication.class, args);
	}

}
