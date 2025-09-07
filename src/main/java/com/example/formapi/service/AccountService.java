package com.example.formapi.service;

import com.example.formapi.dto.request.LoginRequest;
import com.example.formapi.dto.request.RegisterRequest;
import com.example.formapi.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<BaseResponse> create(RegisterRequest request);
    ResponseEntity<BaseResponse> login(LoginRequest request);
}
