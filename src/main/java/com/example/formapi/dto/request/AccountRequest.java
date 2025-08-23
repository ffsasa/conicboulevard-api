package com.example.formapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Password can not blank")
    private String password;
    @Email(message = "Email should be valid")
    private String email;
}
