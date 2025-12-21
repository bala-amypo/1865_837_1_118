package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplier_risk_alerts")
@Data
@NoArgsConstructor
[cite_start]public class SupplierRiskAlert { // [cite: 242]

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long supplierId; [cite_start]// [cite: 245]

    private String alertLevel; [cite_start]// [cite: 250]

    @NotBlank
    @Column(length = 500)
    private String message; [cite_start]// [cite: 256]

    @Column(nullable = false, updatable = false)
    private LocalDateTime alertDate;

    private Boolean resolved = false; [cite_start]// [cite: 265]

    @PrePersist
    protected void onCreate() {
        this.alertDate = LocalDateTime.now();
    }
}