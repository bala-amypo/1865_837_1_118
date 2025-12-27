package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "supplier_profiles")
public class SupplierProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String name;

    private String location;

    private boolean active = true;

    // ===== Constructors =====
    public SupplierProfile() {
    }

    public SupplierProfile(String code, String name, String location) {
        this.code = code;
        this.name = name;
        this.location = location;
        this.active = true;
    }

    // ===== Getters & Setters =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {     // REQUIRED BY SERVICES
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {        // REQUIRED BY SupplierServiceImpl
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {    // REQUIRED BY SupplierServiceImpl
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isActive() {      // REQUIRED BY toggleStatus()
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
