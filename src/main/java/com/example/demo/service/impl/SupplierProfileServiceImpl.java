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

    public SupplierProfile createSupplier(SupplierProfile supplier) {
        supplier.setActive(true);
        return repository.save(supplier);
    }

    public SupplierProfile toggleStatus(Long supplierId) {
        SupplierProfile supplier = repository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setActive(!supplier.isActive());
        return repository.save(supplier);
    }

    public Optional<SupplierProfile> findByCode(String supplierCode) {
        return repository.findBySupplierCode(supplierCode);
    }

    public List<SupplierProfile> findAll() {
        return repository.findAll();
    }
}
