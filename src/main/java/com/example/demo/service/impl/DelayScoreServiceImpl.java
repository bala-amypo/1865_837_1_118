package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.DelayScoreService;
import com.example.demo.service.SupplierRiskAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
[cite_start]public class DelayScoreServiceImpl implements DelayScoreService { // [cite: 556]

    private final DelayScoreRecordRepository delayScoreRepository;
    private final PurchaseOrderRecordRepository poRepository;
    private final DeliveryRecordRepository deliveryRepository;
    private final SupplierProfileRepository supplierRepository;
    private final SupplierRiskAlertService riskAlertService;

    @Override
    @Transactional
    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepository.findById(poId)
                .orElseThrow(() -> new ResourceNotFoundException("PO not found"));

        SupplierProfile supplier = supplierRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier"); [cite_start]// [cite: 566]
        }

        List<DeliveryRecord> deliveries = deliveryRepository.findByPoId(poId);
        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries"); [cite_start]// [cite: 567]
        }

        // Logic using latest delivery
        DeliveryRecord latestDelivery = deliveries.get(deliveries.size() - 1);
        long delayDays = ChronoUnit.DAYS.between(po.getPromisedDeliveryDate(), latestDelivery.getActualDeliveryDate());

        String severity;
        double score;

        [cite_start]// [cite: 211-224] Logic for Delay Scoring
        if (delayDays <= 0) {
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
        record.setSupplierId(supplier.getId());
        record.setDelayDays((int) delayDays);
        record.setDelaySeverity(severity);
        record.setScore(score);
        
        DelayScoreRecord saved = delayScoreRepository.save(record);
        
        // Trigger Risk Alert Check
        checkAndCreateAlert(supplier.getId());
        
        return saved;
    }
    
    private void checkAndCreateAlert(Long supplierId) {
        List<DelayScoreRecord> scores = delayScoreRepository.findBySupplierId(supplierId);
        if (scores.isEmpty()) return;

        double avgScore = scores.stream().mapToDouble(DelayScoreRecord::getScore).average().orElse(0.0);
        
        String level;
        [cite_start]// [cite: 251-255] Logic for Risk Alert
        if (avgScore >= 75) level = "LOW";
        else if (avgScore >= 50) level = "MEDIUM";
        else level = "HIGH";

        SupplierRiskAlert alert = new SupplierRiskAlert();
        alert.setSupplierId(supplierId);
        alert.setAlertLevel(level);
        alert.setMessage("Computed risk level: " + level + " based on average score: " + avgScore);
        riskAlertService.createAlert(alert);
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayScoreRepository.findBySupplierId(supplierId);
    }

    @Override
    public Optional<DelayScoreRecord> getScoreById(Long id) {
        return delayScoreRepository.findById(id);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayScoreRepository.findAll();
    }
}