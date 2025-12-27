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

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRecordRepository poRepo;
    private final SupplierProfileRepository supplierRepo;

    public PurchaseOrderServiceImpl(
            PurchaseOrderRecordRepository poRepo,
            SupplierProfileRepository supplierRepo) {
        this.poRepo = poRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {
        supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Invalid supplier"));
        return poRepo.save(po);
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepo.findAll();
    }

    @Override
    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return poRepo.findById(id);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepo.findBySupplierId(supplierId);
    }
}
