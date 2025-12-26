package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DelayScoreServiceImpl {

    private final DelayScoreRecordRepository repo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertServiceImpl alertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository repo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierProfileRepository supplierRepo,
            SupplierRiskAlertServiceImpl alertService) {
        this.repo = repo;
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

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord d = deliveries.get(0);
        long delayDays = ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(),
                d.getActualDeliveryDate());

        DelayScoreRecord r = new DelayScoreRecord();
        r.setPoId(poId);
        r.setSupplierId(po.getSupplierId());
        r.setDelayDays((int) Math.max(delayDays, 0));

        if (delayDays <= 0) {
            r.setDelaySeverity("ON_TIME");
            r.setScore(100.0);
        } else if (delayDays <= 3) {
            r.setDelaySeverity("MINOR");
            r.setScore(90.0);
        } else {
            r.setDelaySeverity("SEVERE");
            r.setScore(50.0);
        }

        return repo.save(r);
    }

    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return repo.findBySupplierId(supplierId);
    }

    public List<DelayScoreRecord> getAllScores() {
        return repo.findAll();
    }
}
