package com.example.formapi.service;

import com.example.formapi.dto.request.AccountRequest;
import com.example.formapi.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    ResponseEntity<BaseResponse> create(AccountRequest request);
}
