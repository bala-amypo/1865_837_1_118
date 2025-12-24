package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;

import java.util.List;
import java.util.Optional;

public class SupplierProfileServiceImpl {
    private final SupplierProfileRepository supplierRepo;

    public SupplierProfileServiceImpl(SupplierProfileRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public SupplierProfile getSupplierById(Long id) {
        return supplierRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    public SupplierProfile createSupplier(SupplierProfile supplier) {
        if (supplier.getActive() == null) supplier.setActive(true);
        supplierRepo.save(supplier); // ignore return
        return supplier;
    }

    public List<SupplierProfile> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile s = getSupplierById(id);
        s.setActive(active);
        supplierRepo.save(s);
        return s;
    }

    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return supplierRepo.findBySupplierCode(code);
    }
}
