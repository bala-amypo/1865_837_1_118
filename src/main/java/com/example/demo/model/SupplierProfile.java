package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "supplier_profiles")
public class SupplierProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔴 MUST MATCH repository method findBySupplierCode()
    @Column(unique = true, nullable = false)
    private String supplierCode;

    private String name;

    private String location;

    private boolean active = true;

    // ===== Constructors =====
    public SupplierProfile() {
    }

    public SupplierProfile(String supplierCode, String name, String location) {
        this.supplierCode = supplierCode;
        this.name = name;
        this.location = location;
        this.active = true;
    }

    // ===== Getters & Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // used by services/tests
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
