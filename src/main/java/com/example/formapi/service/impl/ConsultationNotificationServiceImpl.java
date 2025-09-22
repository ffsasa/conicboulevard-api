package com.example.formapi.service.impl;

import com.example.formapi.dto.request.ConsultationRequest;
import com.example.formapi.service.ConsultationNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultationNotificationServiceImpl implements ConsultationNotificationService {

    private final JavaMailSender mailSender;

    private static final String TO_EMAIL = "cuongcoach6879@gmail.com";

    @Value("${spring.mail.username:}")
    private String fromEmail;

    @Override
    @Async
    public void sendRegistrationNotification(ConsultationRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(TO_EMAIL);
        if (fromEmail != null && !fromEmail.isBlank()) {
            message.setFrom(fromEmail);
        }

        message.setSubject("1 khách hàng đăng ký mới");
        message.setText("🔔 Có người vừa đăng ký thông tin dự án: " + request.getProjectName());

        try {
            mailSender.send(message);
            log.info("Email thông báo tư vấn đã được gửi tới {} cho dự án {}", TO_EMAIL, request.getProjectName());
        } catch (MailException ex) {
            log.error("Gửi email thất bại cho dự án {}: {}", request.getProjectName(), ex.getMessage(), ex);
        }
    }
}
