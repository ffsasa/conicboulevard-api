package com.example.formapi.service.impl;

import com.example.formapi.dto.request.AccountRequest;
import com.example.formapi.dto.response.BaseResponse;
import com.example.formapi.entity.Account;
import com.example.formapi.entity.Role;
import com.example.formapi.entity.RoleEnum;
import com.example.formapi.repository.AccountRepository;
import com.example.formapi.repository.RoleRepository;
import com.example.formapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseEntity<BaseResponse> create(AccountRequest request) {
        Role role = roleRepository.findByName(RoleEnum.ADMIN);

        Account entity = new Account();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setRole(role);
        repository.save(entity);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new BaseResponse(HttpStatus.ACCEPTED.toString(), "Create successful", entity));
    }
}
