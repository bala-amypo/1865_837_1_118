package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierService;
import com.example.demo.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierProfileRepository repository;

    public SupplierServiceImpl(SupplierProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public SupplierProfile save(SupplierProfile supplier) {
        return repository.save(supplier);
    }

    @Override
    public SupplierProfile findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BadRequestException("Supplier not found"));
    }

    @Override
    public List<SupplierProfile> findAll() {
        return repository.findAll();
    }

    @Override
    public SupplierProfile update(Long id, SupplierProfile supplier) {
        SupplierProfile existing = findById(id);
        supplier.setId(existing.getId());   // preserve ID
        return repository.save(supplier);
    }

    @Override
    public void delete(Long id) {
        SupplierProfile supplier = findById(id);
        repository.delete(supplier);
    }
}
