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
import com.example.demo.service.DelayScoreService;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository delayScoreRecordRepository;
    private final PurchaseOrderRecordRepository purchaseOrderRecordRepository;
    private final DeliveryRecordRepository deliveryRecordRepository;
    private final SupplierProfileRepository supplierProfileRepository;
    private final SupplierRiskAlertService supplierRiskAlertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository delayScoreRecordRepository,
            PurchaseOrderRecordRepository purchaseOrderRecordRepository,
            DeliveryRecordRepository deliveryRecordRepository,
            SupplierProfileRepository supplierProfileRepository,
            SupplierRiskAlertService supplierRiskAlertService) {

        this.delayScoreRecordRepository = delayScoreRecordRepository;
        this.purchaseOrderRecordRepository = purchaseOrderRecordRepository;
        this.deliveryRecordRepository = deliveryRecordRepository;
        this.supplierProfileRepository = supplierProfileRepository;
        this.supplierRiskAlertService = supplierRiskAlertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        PurchaseOrderRecord po = purchaseOrderRecordRepository.findById(poId)
                .orElseThrow(() -> new BadRequestException("PO not found"));

        SupplierProfile supplier = supplierProfileRepository.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found"));

        if (!Boolean.TRUE.equals(supplier.getActive())) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries =
                deliveryRecordRepository.findByPoId(poId);

        if (deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord delivery = deliveries.get(0);

        long delayDays = ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(),
                delivery.getActualDeliveryDate()
        );

        int finalDelayDays = (int) Math.max(delayDays, 0);

        DelayScoreRecord record = new DelayScoreRecord();
        record.setPoId(poId);
        record.setSupplierId(po.getSupplierId());
        record.setDelayDays(finalDelayDays);

        if (finalDelayDays == 0) {
            record.setDelaySeverity("ON_TIME");
            record.setScore(100.0);
        } else if (finalDelayDays <= 3) {
            record.setDelaySeverity("MINOR");
            record.setScore(90.0);
        } else {
            record.setDelaySeverity("SEVERE");
            record.setScore(50.0);
        }

        return delayScoreRecordRepository.save(record);
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
