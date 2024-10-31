package com.backend.appointment.appointment_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Configuracion {
    	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/imagen/**")
				.allowedOrigins("http://localhost:5173", "http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
				registry.addMapping("/appointment/**")
				.allowedOrigins("http://localhost:5173", "http://localhost:3000")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
			}
		};
	}
}
