package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service   // ✅ REQUIRED FOR SPRING
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRecordRepository purchaseOrderRecordRepository;
    private final SupplierProfileRepository supplierProfileRepository;

    public PurchaseOrderServiceImpl(
            PurchaseOrderRecordRepository purchaseOrderRecordRepository,
            SupplierProfileRepository supplierProfileRepository) {

        this.purchaseOrderRecordRepository = purchaseOrderRecordRepository;
        this.supplierProfileRepository = supplierProfileRepository;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord purchaseOrderRecord) {

        SupplierProfile supplier = supplierProfileRepository
                .findById(purchaseOrderRecord.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Invalid supplierId"));

        if (!Boolean.TRUE.equals(supplier.getActive())) {
            throw new BadRequestException("Supplier must be active");
        }

        return purchaseOrderRecordRepository.save(purchaseOrderRecord);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return purchaseOrderRecordRepository.findBySupplierId(supplierId);
    }

    @Override
    public Optional<PurchaseOrderRecord> getPOById(Long poId) {
        return purchaseOrderRecordRepository.findById(poId);
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return purchaseOrderRecordRepository.findAll();
    }
}
