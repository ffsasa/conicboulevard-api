package com.example.formapi.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ConsultationRequest {
    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Column(nullable = false, columnDefinition = "NVARCHAR(20)")
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(0|\\+84)(\\d{9})$",
            message = "Số điện thoại không hợp lệ (phải bắt đầu bằng 0 hoặc +84 và có 10 chữ số)"
    )
    private String phoneNumber;

    @NotBlank(message = "Consult need is required")
    private String consultNeed;

    @NotBlank(message = "Project name is required")
    private String projectName;
}
