/*
 * Copyright (c) 2024. Loollablk
 *
 * All right reserved.
 */

package com.loollablk.sseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.loollablk","com.loollablk.sseservice.config"})
public class SseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SseServiceApplication.class, args);
	}

}
