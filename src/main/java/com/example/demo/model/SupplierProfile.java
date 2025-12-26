package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplier_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String supplierCode;
    
    private String supplierName;
    private String email;
    private String phone;
    
    private Boolean active = true; 
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if(this.active == null) this.active = true;
    }
}