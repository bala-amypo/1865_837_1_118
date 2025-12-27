package com.example.demo.service.impl;

import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.SupplierService;
import com.example.demo.exception.BadRequestException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierProfileRepository supplierRepo;

    public SupplierServiceImpl(SupplierProfileRepository supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    // ---------------- CREATE SUPPLIER ----------------
    @Override
    public SupplierProfile createSupplier(SupplierProfile supplier) {
        if (supplier == null) {
            throw new BadRequestException("Supplier cannot be null");
        }
        return supplierRepo.save(supplier);
    }

    // ---------------- TOGGLE ACTIVE STATUS ----------------
    @Override
    public SupplierProfile toggleStatus(Long supplierId) {
        SupplierProfile supplier = supplierRepo.findById(supplierId)
                .orElseThrow(() -> new BadRequestException("Supplier not found"));

        supplier.setActive(!supplier.isActive());
        return supplierRepo.save(supplier);
    }

    // ---------------- FIND BY CODE ----------------
    @Override
    public SupplierProfile findByCode(String supplierCode) {
        return supplierRepo.findBySupplierCode(supplierCode)
                .orElseThrow(() -> new BadRequestException("Supplier not found"));
    }

    // ---------------- FIND ALL ----------------
    @Override
    public List<SupplierProfile> findAll() {
        return supplierRepo.findAll();
    }
}
