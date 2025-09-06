package com.example.formapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FormApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FormApiApplication.class, args);
    }
}
