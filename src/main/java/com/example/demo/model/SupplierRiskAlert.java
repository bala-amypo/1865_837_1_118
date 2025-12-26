package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "supplier_risk_alerts")
public class SupplierRiskAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long supplierId;

    private String alertLevel; // LOW, MEDIUM, HIGH
    private String message;
    private LocalDateTime alertDate;
    private Boolean resolved = false;
}