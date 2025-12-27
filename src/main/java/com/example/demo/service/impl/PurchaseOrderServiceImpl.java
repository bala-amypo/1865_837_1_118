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
    public PurchaseOrderRecord createPO(PurchaseOrderRecord po) {
        SupplierProfile supplier = supplierRepo.findById(po.getSupplier().getId())
                .orElseThrow(() -> new BadRequestException("Invalid supplierId"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        po.setSupplier(supplier);
        return poRepo.save(po);
    }

    @Override
    public Optional<PurchaseOrderRecord> getById(Long id) {
        return poRepo.findById(id);
    }

    @Override
    public List<PurchaseOrderRecord> getBySupplier(Long supplierId) {
        return poRepo.findBySupplierId(supplierId);
    }
}
