package com.example.formapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${googledrive.api.base-url}")
    private String googleDriveBaseUrl;

    @Bean(name = "googleDriveWebClient")
    public WebClient googleDriveWebClient() {
        return WebClient.builder()
                .baseUrl(googleDriveBaseUrl)
                .build();
    }
}
