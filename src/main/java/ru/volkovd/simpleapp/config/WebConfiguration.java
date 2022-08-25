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
        registry.addMapping("/**").allowedMethods("*");

        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedOrigins("http://192.168.0.110:3000")
                .allowedOrigins("http://192.168.0.111:3000")
                .allowedMethods("PUT", "DELETE",
                "GET", "POST");
    }
}