package com.example.demo.service.impl;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRecordRepository poRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRecordRepository poRepository) {
        this.poRepository = poRepository;
    }

    @Override
    public PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po) {
        return poRepository.save(po);
    }

    @Override
    public Optional<PurchaseOrderRecord> getPOById(Long id) {
        return poRepository.findById(id);
    }

    @Override
    public List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId) {
        return poRepository.findBySupplierId(supplierId);
    }

    @Override
    public List<PurchaseOrderRecord> getAllPurchaseOrders() {
        return poRepository.findAll();
    }
}
