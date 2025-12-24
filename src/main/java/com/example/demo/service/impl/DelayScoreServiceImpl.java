package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DelayScoreRecord;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class DelayScoreServiceImpl {
    private final DelayScoreRecordRepository delayRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertServiceImpl alertService;

    public DelayScoreServiceImpl(DelayScoreRecordRepository delayRepo,
                                 PurchaseOrderRecordRepository poRepo,
                                 DeliveryRecordRepository deliveryRepo,
                                 SupplierProfileRepository supplierRepo,
                                 SupplierRiskAlertServiceImpl alertService) {
        this.delayRepo = delayRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.alertService = alertService;
    }

    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() -> new BadRequestException("PO not found"));
        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found"));
        if (!Boolean.TRUE.equals(supplier.getActive())) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord latest = deliveries.get(0);
        int delayDays = (int) ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(), latest.getActualDeliveryDate());
        int normalized = Math.max(delayDays, 0);

        DelayScoreRecord record = new DelayScoreRecord();
        record.setPoId(poId);
        record.setSupplierId(po.getSupplierId());
        record.setDelayDays(normalized);

        if (normalized == 0) {
            record.setDelaySeverity("ON_TIME");
            record.setScore(100.0);
        } else if (normalized <= 3) {
            record.setDelaySeverity("MINOR");
            record.setScore(80.0); // ensure < 100.0
        } else if (normalized <= 7) {
            record.setDelaySeverity("MODERATE");
            record.setScore(50.0);
        } else {
            record.setDelaySeverity("SEVERE");
            record.setScore(0.0);
        }

        return delayRepo.save(record);
    }

    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayRepo.findBySupplierId(supplierId);
    }

    public List<DelayScoreRecord> getAllScores() {
        return delayRepo.findAll();
    }
}
