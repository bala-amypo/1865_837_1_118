package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import java.util.*;

public class SupplierProfileServiceImpl {

    private final SupplierProfileRepository repository;

    public SupplierProfileServiceImpl(SupplierProfileRepository repository) {
        this.repository = repository;
    }

    public SupplierProfile createSupplier(SupplierProfile supplier) {
        return repository.save(supplier);
    }

    public SupplierProfile getSupplierById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public List<SupplierProfile> getAllSuppliers() {
        return repository.findAll();
    }

    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = getSupplierById(id);
        supplier.setActive(active);
        return repository.save(supplier);
    }

    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return repository.findBySupplierCode(code);
    }
}
