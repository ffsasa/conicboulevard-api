package com.example.formapi.client;

import com.example.formapi.exception.ExternalApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class ExternalApiClient {

    private final WebClient googleDriveWebClient;

    public String uploadFile(String filename) {
        try {
            ClassPathResource resource = new ClassPathResource("credentials/service-account.json");
            // example: upload metadata to Drive
            Map<?, ?> response = googleDriveWebClient.post()
                    .uri("/files")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(Map.of("name", filename))
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            return (String) response.get("id");
        } catch (Exception e) {
            throw new ExternalApiException("Failed to upload file to Google Drive", e);
        }
    }

    public void notifyNewForm(Long id, String name) {
        // Example: send a notification
        googleDriveWebClient.post()
                .uri("/forms/notify")
                .bodyValue(Map.of("id", id, "name", name))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
