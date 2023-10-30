package com.example.apiffirebase.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**").allowedOrigins("https://mobisiweb.onrender.com").allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(false);
        registry.addMapping("/**").allowedOrigins("https://apifirebase.onrender.com").allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
