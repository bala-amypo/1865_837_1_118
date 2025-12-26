package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DelayScoreServiceImpl {

    private final DelayScoreRecordRepository scoreRepository;
    private final PurchaseOrderRecordRepository poRepository;
    private final DeliveryRecordRepository deliveryRepository;
    private final SupplierProfileRepository supplierRepository;
    private final SupplierRiskAlertServiceImpl alertService;

    // Constructor matching the test setup exactly
    public DelayScoreServiceImpl(DelayScoreRecordRepository scoreRepository,
                                 PurchaseOrderRecordRepository poRepository,
                                 DeliveryRecordRepository deliveryRepository,
                                 SupplierProfileRepository supplierRepository,
                                 SupplierRiskAlertServiceImpl alertService) {
        this.scoreRepository = scoreRepository;
        this.poRepository = poRepository;
        this.deliveryRepository = deliveryRepository;
        this.supplierRepository = supplierRepository;
        this.alertService = alertService;
    }

    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepository.findById(poId)
                .orElseThrow(() -> new BadRequestException("Invalid PO"));

        // Test: testComputeDelayScore_inactiveSupplier
        SupplierProfile supplier = supplierRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found"));
        
        if (!Boolean.TRUE.equals(supplier.getActive())) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepository.findByPoId(poId);
        
        // Test: testComputeDelayScore_noDeliveries
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries for PO");
        }

        // Logic: Use latest delivery for calculation
        DeliveryRecord lastDelivery = deliveries.get(deliveries.size() - 1);
        long delayDays = ChronoUnit.DAYS.between(po.getPromisedDeliveryDate(), lastDelivery.getActualDeliveryDate());

        // Logic: Severity Levels (0=ON_TIME, 1-3=MINOR, 4-7=MODERATE, 8+=SEVERE)
        String severity;
        double score;
        if (delayDays <= 0) {
            delayDays = 0; // Normalize early to 0
            severity = "ON_TIME";
            score = 100.0;
        } else if (delayDays <= 3) {
            severity = "MINOR";
            score = 75.0;
        } else if (delayDays <= 7) {
            severity = "MODERATE";
            score = 50.0;
        } else {
            severity = "SEVERE";
            score = 0.0;
        }

        DelayScoreRecord record = new DelayScoreRecord();
        record.setPoId(poId);
        record.setSupplierId(po.getSupplierId());
        record.setDelayDays((int) delayDays);
        record.setDelaySeverity(severity);
        record.setScore(score);
        record.setComputedAt(LocalDateTime.now());

        return scoreRepository.save(record);
    }

    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return scoreRepository.findBySupplierId(supplierId);
    }
    
    public List<DelayScoreRecord> getAllScores() {
        return scoreRepository.findAll();
    }
}