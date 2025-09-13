package com.example.formapi.service;

import com.example.formapi.dto.request.ConsultationRequest;
import com.example.formapi.dto.response.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ConsultationService {
    ResponseEntity<BaseResponse<?>> create(ConsultationRequest request);
    ResponseEntity<BaseResponse<?>> check(UUID uuid);
    ResponseEntity<BaseResponse<?>> getAll(String projectName, Pageable pageable);
    ResponseEntity<BaseResponse<?>> delete(UUID uuid);
}
