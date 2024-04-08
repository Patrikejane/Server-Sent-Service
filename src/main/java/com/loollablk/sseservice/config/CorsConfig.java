/*
 * Copyright (c) 2024. Loollablk
 *
 * All right reserved.
 */

package com.loollablk.sseservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("Corse Configurations");
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // Allow all origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific HTTP methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true) // Allow credentials (e.g., cookies)
                .maxAge(3600); // Max age of the CORS pre-flight request
    }



}
