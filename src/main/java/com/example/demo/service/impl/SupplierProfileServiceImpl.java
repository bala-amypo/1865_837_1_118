package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;

import java.util.List;
import java.util.Optional;

public class SupplierProfileServiceImpl implements SupplierProfileService {

    private final SupplierProfileRepository repository;

    public SupplierProfileServiceImpl(SupplierProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        Optional<SupplierProfile> opt = repository.findById(id);
        if (opt.isEmpty()) {
            throw new ResourceNotFoundException("Supplier not found");
        }
        return opt.get();
    }

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplierProfile) {
        return repository.save(supplierProfile);
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return repository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long supplierId, boolean active) {
        SupplierProfile supplier = getSupplierById(supplierId);
        supplier.setActive(active);
        return repository.save(supplier);
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String supplierCode) {
        return repository.findBySupplierCode(supplierCode);
    }
}
