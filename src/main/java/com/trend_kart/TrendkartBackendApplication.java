package com.trend_kart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrendkartBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrendkartBackendApplication.class, args);
	}

}
