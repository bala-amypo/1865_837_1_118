package com.example.demo.service;

import com.example.demo.model.SupplierProfile;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    // Create a new supplier
    SupplierProfile createSupplier(SupplierProfile supplier);

    // Enable / Disable supplier (toggle active status)
    SupplierProfile toggleStatus(Long id);

    // Find supplier by unique supplier code
    Optional<SupplierProfile> findByCode(String code);

    // Fetch all suppliers
    List<SupplierProfile> findAll();
}
