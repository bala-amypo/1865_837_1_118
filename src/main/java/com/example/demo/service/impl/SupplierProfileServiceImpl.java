package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierProfileServiceImpl {

    private final SupplierProfileRepository repository;

    public SupplierProfileServiceImpl(SupplierProfileRepository repository) {
        this.repository = repository;
    }

    public SupplierProfile save(SupplierProfile supplier) {
        return repository.save(supplier);
    }

    public List<SupplierProfile> findAll() {
        return repository.findAll();
    }

    public Optional<SupplierProfile> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<SupplierProfile> findBySupplierCode(String code) {
        return repository.findBySupplierCode(code);
    }

    public SupplierProfile toggleStatus(Long id) {
        SupplierProfile supplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplier.setActive(!supplier.isActive());
        return repository.save(supplier);
    }
}
