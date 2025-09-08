package com.example.formapi.service;

import com.example.formapi.dto.request.ConsultationRequest;
import com.example.formapi.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface ConsultationService {
    ResponseEntity<BaseResponse<?>> create(ConsultationRequest request);
}
