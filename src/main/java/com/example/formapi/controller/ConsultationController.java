package com.example.formapi.controller;

import com.example.formapi.dto.request.ConsultationRequest;
import com.example.formapi.dto.response.BaseResponse;
import com.example.formapi.service.ConsultationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/consultation")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<BaseResponse<?>> create(@Valid @RequestBody ConsultationRequest request) {
        return consultationService.create(request);
    }

    @PutMapping("/check/{id}")
    public ResponseEntity<BaseResponse<?>> check(@PathVariable UUID id) {
        return consultationService.check(id);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable UUID id) {
        return consultationService.delete(id);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<?>> getAll(@RequestParam String projectName, Pageable pageable) {
        return consultationService.getAll(projectName, pageable);
    }
}
