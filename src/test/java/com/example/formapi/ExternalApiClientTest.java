package com.example.formapi;

import com.example.formapi.client.ExternalApiClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ExternalApiClientTest {

    private final ExternalApiClient client = new ExternalApiClient(WebClient.builder().baseUrl("http://localhost:8080").build());

    @Test
    void shouldThrowOnFailure() {
        assertThrows(Exception.class, () -> client.uploadFile("test"));
    }
}
