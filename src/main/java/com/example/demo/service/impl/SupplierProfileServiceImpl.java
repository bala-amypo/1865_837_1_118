package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;

import java.util.List;
import java.util.Optional;

public class SupplierProfileServiceImpl {

    // 🔴 Mockito injects this
    SupplierProfileRepository supplierProfileRepository;

    // ✅ REQUIRED no-arg constructor
    public SupplierProfileServiceImpl() {
    }

    // optional constructor (safe)
    public SupplierProfileServiceImpl(SupplierProfileRepository repo) {
        this.supplierProfileRepository = repo;
    }

    public SupplierProfile createSupplier(SupplierProfile supplier) {
        if (supplier.getActive() == null) {
            supplier.setActive(true);
        }
        return supplierProfileRepository.save(supplier);
    }

    public SupplierProfile getSupplierById(Long id) {
        return supplierProfileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    public List<SupplierProfile> getAllSuppliers() {
        return supplierProfileRepository.findAll();
    }

    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile supplier = getSupplierById(id);
        supplier.setActive(active);
        return supplierProfileRepository.save(supplier);
    }

    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return supplierProfileRepository.findBySupplierCode(code);
    }
}
