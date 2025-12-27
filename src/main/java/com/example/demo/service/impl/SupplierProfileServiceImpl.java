package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierProfileRepository supplierRepo;

    public SupplierServiceImpl(SupplierProfileRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        supplier.setActive(true);   // ✅ tests expect default active = true
        return supplierRepo.save(supplier);
    }

    @Override
    public SupplierProfile toggleStatus(Long id) {
        SupplierProfile supplier = supplierRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setActive(!supplier.getActive());
        return supplierRepo.save(supplier);
    }

    @Override
    public Optional<SupplierProfile> findByCode(String code) {
        return supplierRepo.findBySupplierCode(code);
    }

    @Override
    public List<SupplierProfile> findAll() {
        return supplierRepo.findAll();
    }
}
