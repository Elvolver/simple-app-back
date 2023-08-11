package ru.volkovd.simpleapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedMethods("*")
                .allowedOrigins("http://localhost:3000").allowedMethods("PUT", "DELETE", "GET", "POST")
                .allowedOrigins("http://192.168.1.50:3000").allowedMethods("PUT", "DELETE", "GET", "POST");
    }
}