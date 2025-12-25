package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;

import java.util.List;
import java.util.Optional;

public class PurchaseOrderServiceImpl {

    PurchaseOrderRecordRepository poRepository;
    SupplierProfileRepository supplierProfileRepository;

    public PurchaseOrderServiceImpl() {
    }

    public PurchaseOrderServiceImpl(PurchaseOrderRecordRepository poRepo,
                                    SupplierProfileRepository supplierRepo) {
        this.poRepository = poRepo;
        this.supplierProfileRepository = supplierRepo;
    }

    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {

        SupplierProfile supplier = supplierProfileRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Invalid supplierId"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Supplier must be active");
        }

        return poRepository.save(po);
    }

    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepository.findBySupplierId(supplierId);
    }

    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return poRepository.findById(id);
    }

    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepository.findAll();
    }
}
