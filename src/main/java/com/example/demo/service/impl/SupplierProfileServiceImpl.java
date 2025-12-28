package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierProfileServiceImpl implements SupplierProfileService {
    private final SupplierProfileRepository repository;

    public SupplierProfileServiceImpl(SupplierProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        [cite_start]// Rule: Check duplicate code [cite: 322]
        if(repository.findBySupplierCode(supplier.getSupplierCode()).isPresent()){
            throw new IllegalArgumentException("Duplicate supplier code");
        }
        return repository.save(supplier);
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
        [cite_start]// Rule: Throw ResourceNotFoundException if missing [cite: 326]
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    @Override
    public Optional<SupplierProfile> getBySupplierCode(String code) {
        return repository.findBySupplierCode(code);
    }

    @Override
    public List<SupplierProfile> getAllSuppliers() {
        return repository.findAll();
    }

    @Override
    public SupplierProfile updateSupplierStatus(Long id, boolean active) {
        SupplierProfile s = getSupplierById(id);
        s.setActive(active);
        return repository.save(s);
    }
}