package com.example.formapi.service.impl;

import com.example.formapi.dto.request.ConsultationRequest;
import com.example.formapi.dto.response.BaseResponse;
import com.example.formapi.entity.Consultation;
import com.example.formapi.repository.ConsultationRepository;
import com.example.formapi.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository repository;
    private final JavaMailSender mailSender;

    private static final String TO_EMAIL = "thean123456asd@gmail.com";

    @Value("${spring.mail.username:}")
    private String fromEmail; // Nên trùng username khi dùng Gmail

    @Override
    public ResponseEntity<BaseResponse<?>> create(ConsultationRequest request) {
        // 1) Lưu DB
        Consultation entity = Consultation.builder()
                .customerName(request.getCustomerName())
                .phoneNumber(request.getPhoneNumber())
                .consultNeed(request.getConsultNeed())
                .projectName(request.getProjectName())
                .build();
        repository.save(entity);

        // 2) Gửi email với template
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(TO_EMAIL);
        if (fromEmail != null && !fromEmail.isBlank()) {
            message.setFrom(fromEmail);
        }
        message.setSubject("1 khách hàng đăng ký mới");
        message.setText("🔔 Có người vừa đăng ký thông tin dự án: " + request.getProjectName());

        try {
            mailSender.send(message);
        } catch (MailException ex) {
            // Ghi log nếu muốn, không chặn tạo mới
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>("201", "Created", null));
    }
}

