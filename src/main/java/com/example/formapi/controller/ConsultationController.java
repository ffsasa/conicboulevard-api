package com.example.formapi.controller;

import com.example.formapi.dto.request.ConsultationRequest;
import com.example.formapi.dto.response.BaseResponse;
import com.example.formapi.service.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consultation")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> create(@Valid @RequestBody ConsultationRequest request) {
        return consultationService.create(request);
    }
}
