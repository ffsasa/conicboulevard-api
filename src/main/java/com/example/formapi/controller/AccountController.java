package com.example.formapi.controller;

import com.example.formapi.dto.request.AccountRequest;
import com.example.formapi.dto.response.BaseResponse;
import com.example.formapi.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody AccountRequest request) {
        return accountService.create(request);
    }
}
