package com.rishabh.musicstream.appconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class CorsConfig {

// Accept multiple frontend origins separated by commas.
// Example:
// FRONTEND_URLS=http://localhost:3000,https://rishabh-147.github.io
    @Value("${app.frontend.urls}")
    private String frontendUrls;

    @Bean
    public WebMvcConfigurer corsConfigurer() {

        log.info("Cross Origin set to : {}", frontendUrls);
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry.addMapping("/**")
                        .allowedOrigins(frontendUrls.split(","))
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
