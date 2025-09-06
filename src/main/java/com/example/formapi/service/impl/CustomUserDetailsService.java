package com.example.formapi.service.impl;

import com.example.formapi.entity.Account;
import com.example.formapi.repository.AccountRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository repo;
    public CustomUserDetailsService(AccountRepository repo) { this.repo = repo; }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Account acc = repo.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var authority = new SimpleGrantedAuthority("ROLE_" + acc.getRole().getName());
        return org.springframework.security.core.userdetails.User.withUsername(acc.getEmail())
                .password(acc.getPassword())
                .authorities(authority)
                .disabled(!acc.isActive())
                .build();
    }
}

