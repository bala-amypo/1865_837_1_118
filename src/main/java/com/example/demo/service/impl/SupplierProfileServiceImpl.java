package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SupplierProfileServiceImpl implements SupplierProfileService {
    private final SupplierProfileRepository repository;

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        if(repository.existsBySupplierCode(supplier.getSupplierCode())) {
            throw new IllegalArgumentException("Duplicate supplier code");
        }
        if(supplier.getActive() == null) supplier.setActive(true);
        return repository.save(supplier);
    }

    @Override
    public SupplierProfile getSupplierById(Long id) {
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