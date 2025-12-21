package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "supplier_profiles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"supplier_code"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_code", nullable = false, unique = true)
    private String supplierCode;

    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public SupplierProfile(String supplierCode, String supplierName, String email, String phone, Boolean active) {
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.email = email;
        this.phone = phone;
        this.active = active != null ? active : Boolean.TRUE;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.active == null) this.active = Boolean.TRUE;
    }
}