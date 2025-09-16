package com.example.formapi.service.impl;

import com.example.formapi.dto.request.ConsultationRequest;
import com.example.formapi.dto.response.BaseResponse;
import com.example.formapi.entity.Consultation;
import com.example.formapi.repository.ConsultationRepository;
import com.example.formapi.service.ConsultationNotificationService;
import com.example.formapi.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository repository;

    private final ConsultationNotificationService consultationNotificationService;

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
        consultationNotificationService.sendRegistrationNotification(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>(201, "Created", null));
    }

    @Override
    public ResponseEntity<BaseResponse<?>> check(UUID uuid) {
        Optional<Consultation> consultation = repository.findByIdAndIsActiveTrue(uuid);

        if (consultation.isPresent()) {
            consultation.get().setCheck(true);
            repository.save(consultation.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<>(404, "Not Found", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(200, "OK", true));
    }

    @Override
    public ResponseEntity<BaseResponse<?>> getAll(String projectName, Pageable pageable) {
        Page<Consultation> page = repository.findAllByIsActiveTrueAndProjectName(projectName, pageable);

        // Metadata để FE dễ xử lý
        Map<String, Object> response = new HashMap<>();
        response.put("items", page.getContent());
        response.put("currentPage", page.getNumber());          // số trang hiện tại (0-based)
        response.put("pageSize", page.getSize());               // số phần tử mỗi trang
        response.put("totalItems", page.getTotalElements());    // tổng số bản ghi
        response.put("totalPages", page.getTotalPages());       // tổng số trang
        response.put("isLast", page.isLast());                  // có phải trang cuối không

        return ResponseEntity.ok(
                new BaseResponse<>(200, "Success", response)
        );
    }

    @Override
    public ResponseEntity<BaseResponse<?>> delete(UUID uuid) {
        Optional<Consultation> consultation = repository.findByIdAndIsActiveTrue(uuid);

        if (consultation.isPresent()) {
            consultation.get().setActive(false);
            repository.save(consultation.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BaseResponse<>(404, "Not Found", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(200, "OK", true));
    }
}

