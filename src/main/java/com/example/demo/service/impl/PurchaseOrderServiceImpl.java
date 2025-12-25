package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;

import java.util.List;
import java.util.Optional;

public class PurchaseOrderServiceImpl {

    private final PurchaseOrderRecordRepository poRepo;
    private final SupplierProfileRepository supplierRepo;

    public PurchaseOrderServiceImpl(PurchaseOrderRecordRepository poRepo,
                                    SupplierProfileRepository supplierRepo) {
        this.poRepo = poRepo;
        this.supplierRepo = supplierRepo;
    }

    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {
        SupplierProfile s = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Invalid supplierId"));

        if (!s.getActive())
            throw new BadRequestException("Supplier must be active");

        if (po.getQuantity() <= 0)
            throw new BadRequestException("Quantity must be positive");

        return poRepo.save(po);
    }

    public List<PurchaseOrderRecord> getPOsBySupplier(Long id) {
        return poRepo.findBySupplierId(id);
    }

    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return poRepo.findById(id);
    }

    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepo.findAll();
    }
}
