package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl {

    private final PurchaseOrderRecordRepository poRepository;
    private final SupplierProfileRepository supplierRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRecordRepository poRepository, 
                                  SupplierProfileRepository supplierRepository) {
        this.poRepository = poRepository;
        this.supplierRepository = supplierRepository;
    }

    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {
        // Test: testCreatePurchaseOrder_invalidSupplier
        SupplierProfile supplier = supplierRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Invalid supplierId"));

        // Test: testCreatePurchaseOrder_inactiveSupplier
        if (!Boolean.TRUE.equals(supplier.getActive())) {
            throw new BadRequestException("Supplier must be active");
        }
        
        // Basic validation
        if (po.getQuantity() != null && po.getQuantity() <= 0) {
             throw new BadRequestException("Quantity must be positive");
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