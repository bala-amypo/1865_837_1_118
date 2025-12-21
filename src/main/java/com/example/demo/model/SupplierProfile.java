package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplier_profiles")
@Data
@NoArgsConstructor
[cite_start]public class SupplierProfile { // [cite: 97]

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, length = 50)
    private String supplierCode; [cite_start]// [cite: 100-104]

    @NotBlank
    @Column(length = 200)
    private String supplierName; [cite_start]// [cite: 107-110]

    @Email
    @Column(length = 100)
    private String email; [cite_start]// [cite: 111-114]

    @Column(length = 20)
    private String phone; [cite_start]// [cite: 115-117]

    private Boolean active = true; [cite_start]// [cite: 120-121]

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}