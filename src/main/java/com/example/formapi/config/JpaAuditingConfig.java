package com.example.formapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
public class JpaAuditingConfig {

    @Bean
    public DateTimeProvider auditingDateTimeProvider(
            @Value("${spring.jpa.properties.hibernate.jdbc.time_zone:UTC}") String zoneId) {
        ZoneId databaseZone = ZoneId.of(zoneId);
        return () -> Optional.of(LocalDateTime.now(databaseZone));
    }
}
