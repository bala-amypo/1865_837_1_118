package com.example.demo.model;

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
public class SupplierRiskAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long supplierId;

    private String alertLevel;

    @NotBlank
    @Column(length = 500)
    private String message;

    @Column(nullable = false, updatable = false)
    private LocalDateTime alertDate;

    private Boolean resolved = false;

    @PrePersist
    protected void onCreate() {
        this.alertDate = LocalDateTime.now();
    }
}