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

    private final DelayScoreRecordRepository scoreRepo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;

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
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        PurchaseOrderRecord po = poRepo.findById(poId).orElse(null);
        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId()).orElse(null);

        if (supplier != null && Boolean.FALSE.equals(supplier.getActive())) {
            throw new BadRequestException("Inactive supplier");
        }

        List<DeliveryRecord> deliveries = deliveryRepo.findByPoId(poId);
        if (deliveries == null || deliveries.isEmpty()) {
            throw new BadRequestException("No deliveries");
        }

        DeliveryRecord lastDelivery = deliveries.get(deliveries.size() - 1);

        long delayDays = ChronoUnit.DAYS.between(
                po.getPromisedDeliveryDate(),
                lastDelivery.getActualDeliveryDate()
        );

        if (delayDays < 0) {
            delayDays = 0;
        }

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
