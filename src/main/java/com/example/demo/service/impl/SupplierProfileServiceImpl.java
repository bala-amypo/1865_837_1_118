package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierProfileServiceImpl implements SupplierService {

    private final SupplierProfileRepository supplierRepository;

    public SupplierProfileServiceImpl(SupplierProfileRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        supplier.setActive(true);
        return supplierRepository.save(supplier);
    }

    @Override
    public SupplierProfile toggleStatus(Long supplierId) {
        SupplierProfile supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setActive(!supplier.isActive());
        return supplierRepository.save(supplier);
    }

    @Override
    public Optional<SupplierProfile> findByCode(String supplierCode) {
        return supplierRepository.findBySupplierCode(supplierCode);
    }

    @Override
    public List<SupplierProfile> findAll() {
        return supplierRepository.findAll();
    }
}
