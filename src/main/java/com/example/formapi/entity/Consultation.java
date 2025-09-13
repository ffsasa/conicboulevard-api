package com.example.formapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Consultation extends BaseEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String customerName;   // Họ tên khách hàng
    @Column(nullable = false, columnDefinition = "NVARCHAR(20)")
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(
            regexp = "^(0|\\+84)(\\d{9})$",
            message = "Số điện thoại không hợp lệ (phải bắt đầu bằng 0 hoặc +84 và có 10 chữ số)"
    )
    private String phoneNumber;    // Số điện thoại khách hàng
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String consultNeed;    // Nhu cầu tư vấn
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String projectName;    // Tên dự án cần tư vấn
    private boolean isCheck = false;
}

