package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class DelayScoreServiceImpl {

    private final DelayScoreRecordRepository scoreRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertServiceImpl alertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository scoreRepo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierProfileRepository supplierRepo,
            SupplierRiskAlertServiceImpl alertService
    ) {
        this.scoreRepo = scoreRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.alertService = alertService;
    }

    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() -> new BadRequestException("PO not found"));

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Invalid supplier"));

        if (!supplier.getActive())
            throw new BadRequestException("Inactive supplier");

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty())
            throw new BadRequestException("No deliveries");

        DeliveryRecord d = deliveries.get(0);

        int delay = (int) ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(),
                d.getActualDeliveryDate()
        );
        if (delay < 0) delay = 0;

        DelayScoreRecord score = new DelayScoreRecord();
        score.setPoId(poId);
        score.setSupplierId(po.getSupplierId());
        score.setDelayDays(delay);

        if (delay == 0) {
            score.setDelaySeverity("ON_TIME");
            score.setScore(100.0);
        } else if (delay <= 3) {
            score.setDelaySeverity("MINOR");
            score.setScore(75.0);
        } else {
            score.setDelaySeverity("SEVERE");
            score.setScore(50.0);
        }

        return scoreRepo.save(score);
    }

    public List<DelayScoreRecord> getScoresBySupplier(Long id) {
        return scoreRepo.findBySupplierId(id);
    }

    public List<DelayScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
