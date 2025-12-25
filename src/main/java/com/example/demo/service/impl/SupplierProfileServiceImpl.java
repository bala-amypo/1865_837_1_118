package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;

import java.util.List;
import java.util.Optional;

public class SupplierProfileServiceImpl {

    private final SupplierProfileRepository repo;

    public SupplierProfileServiceImpl(SupplierProfileRepository repo) {
        this.repo = repo;
    }

    public SupplierProfile createSupplier(SupplierProfile s) {
        if (s.getActive() == null) s.setActive(true);
        return repo.save(s);
    }

    public SupplierProfile getSupplierById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    public List<SupplierProfile> getAllSuppliers() {
        return repo.findAll();
    }

    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile s = getSupplierById(id);
        s.setActive(active);
        return repo.save(s);
    }

    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return repo.findBySupplierCode(code);
    }
}
