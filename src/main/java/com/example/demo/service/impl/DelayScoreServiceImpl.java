package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.DelayScoreService;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository scoreRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertService alertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository scoreRepo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierProfileRepository supplierRepo,
            SupplierRiskAlertService alertService) {
        this.scoreRepo = scoreRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.alertService = alertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() -> new BadRequestException("Invalid PO"));

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord last = deliveries.get(deliveries.size() - 1);

        long delayDays = Math.max(
                0,
                ChronoUnit.DAYS.between(
                        po.getPromisedDeliveryDate(),
                        last.getActualDeliveryDate()
                )
        );

        DelayScoreRecord record = new DelayScoreRecord();
        record.setPoId(poId);
        record.setSupplierId(po.getSupplierId());
        record.setDelayDays((int) delayDays);

        if (delayDays == 0) {
            record.setDelaySeverity("ON_TIME");
            record.setScore(100.0);
        } else if (delayDays <= 3) {
            record.setDelaySeverity("MINOR");
            record.setScore(80.0);
        } else {
            record.setDelaySeverity("SEVERE");
            record.setScore(50.0);
        }

        return scoreRepo.save(record);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return scoreRepo.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return scoreRepo.findAll();
    }
}
