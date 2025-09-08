package com.example.formapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private String phoneNumber;    // Số điện thoại khách hàng
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String consultNeed;    // Nhu cầu tư vấn
    @Column(nullable = false, columnDefinition = "NVARCHAR(255)")
    private String projectName;    // Tên dự án cần tư vấn
}

