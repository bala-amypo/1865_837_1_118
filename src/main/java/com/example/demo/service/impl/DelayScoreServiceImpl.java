package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DelayScoreRecord;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.model.SupplierProfile;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.DelayScoreService;
import com.example.demo.service.SupplierRiskAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository delayScoreRecordRepository;
    private final PurchaseOrderRecordRepository poRepository;
    private final DeliveryRecordRepository deliveryRepository;
    private final SupplierProfileRepository supplierProfileRepository;
    private final SupplierRiskAlertService riskAlertService;

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {
        PurchaseOrderRecord po = poRepository.findById(poId)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase order not found"));

        SupplierProfile supplier = supplierProfileRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        if (!supplier.getActive()) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepository.findByPoId(poId);
        if (deliveries == null || deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        // Use latest delivery by actualDeliveryDate
        DeliveryRecord latest = deliveries.stream()
                .max(Comparator.comparing(DeliveryRecord::getActualDeliveryDate))
                .orElse(deliveries.get(0));

        LocalDate promised = po.getPromisedDeliveryDate();
        LocalDate actual = latest.getActualDeliveryDate();
        int delayDays = Math.max(0, (int) (actual.toEpochDay() - promised.toEpochDay()));

        String severity;
        if (delayDays == 0) {
            severity = "ON_TIME";
        } else if (delayDays <= 3) {
            severity = "MINOR";
        } else if (delayDays <= 7) {
            severity = "MODERATE";
        } else {
            severity = "SEVERE";
        }

        double score = Math.max(0, 100 - (delayDays * 5));

        // Enforce one score per PO
        delayScoreRecordRepository.findByPoId(poId).ifPresent(existing -> {
            throw new BadRequestException("Delay score already computed for this PO");
        });

        DelayScoreRecord record = new DelayScoreRecord(supplier.getId(), poId, delayDays, severity, score);
        DelayScoreRecord saved = delayScoreRecordRepository.save(record);

        if ("SEVERE".equals(severity)) {
            SupplierRiskAlert alert = new SupplierRiskAlert(
                    supplier.getId(),
                    "HIGH",
                    "Severe delay detected for PO " + po.getPoNumber() + " (" + delayDays + " days)",
                    null,
                    false
            );
            riskAlertService.createAlert(alert);
        }

        return saved;
    }

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return delayScoreRecordRepository.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return delayScoreRecordRepository.findAll();
    }
}
