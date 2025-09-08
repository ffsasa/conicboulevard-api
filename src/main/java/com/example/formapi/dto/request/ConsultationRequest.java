package com.example.formapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ConsultationRequest {
    @NotBlank(message = "Customer name is required")
    private String customerName;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Consult need is required")
    private String consultNeed;

    @NotBlank(message = "Project name is required")
    private String projectName;
}
