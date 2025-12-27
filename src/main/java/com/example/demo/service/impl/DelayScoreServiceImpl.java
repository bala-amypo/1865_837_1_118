package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DelayScoreServiceImpl {

    private final DelayScoreRecordRepository scoreRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertServiceImpl alertService;

    public DelayScoreServiceImpl(DelayScoreRecordRepository scoreRepo,
                                 PurchaseOrderRecordRepository poRepo,
                                 DeliveryRecordRepository deliveryRepo,
                                 SupplierProfileRepository supplierRepo,
                                 SupplierRiskAlertServiceImpl alertService) {
        this.scoreRepo = scoreRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.alertService = alertService;
    }

    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepo.findById(poId).orElseThrow();
        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId()).orElseThrow();

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord d = deliveries.get(0);
        long delay = ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(),
                d.getActualDeliveryDate()
        );

        DelayScoreRecord record = new DelayScoreRecord();
        record.setPoId(poId);
        record.setSupplierId(po.getSupplierId());
        record.setDelayDays((int) Math.max(delay, 0));

        if (delay <= 0) {
            record.setDelaySeverity("ON_TIME");
            record.setScore(100.0);
        } else if (delay <= 3) {
            record.setDelaySeverity("MINOR");
            record.setScore(80.0);
        } else {
            record.setDelaySeverity("SEVERE");
            record.setScore(50.0);
        }

        return scoreRepo.save(record);
    }

    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return scoreRepo.findBySupplierId(supplierId);
    }

    public List<DelayScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
