package com.example.formapi.service.impl;

import com.example.formapi.dto.request.AccountRequest;
import com.example.formapi.dto.response.BaseResponse;
import com.example.formapi.entity.Account;
import com.example.formapi.entity.Role;
import com.example.formapi.entity.RoleEnum;
import com.example.formapi.repository.AccountRepository;
import com.example.formapi.repository.RoleRepository;
import com.example.formapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<BaseResponse> create(AccountRequest request) {
        if (repository.existsByEmailIgnoreCase(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new BaseResponse<>("409", "Email already in use", null));
        }

        Role role = roleRepository.findByName(RoleEnum.ADMIN);
        Account entity = Account.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        repository.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponse<>("201", "Created", null));
    }
}

