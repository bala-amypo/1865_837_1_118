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

import java.time.temporal.ChronoUnit;
import java.util.List;

public class DelayScoreServiceImpl implements DelayScoreService {

    private final DelayScoreRecordRepository repo;
    private final PurchaseOrderRecordRepository poRepo;
    private final DeliveryRecordRepository deliveryRepo;
    private final SupplierProfileRepository supplierRepo;
    private final SupplierRiskAlertService alertService;

    public DelayScoreServiceImpl(
            DelayScoreRecordRepository repo,
            PurchaseOrderRecordRepository poRepo,
            DeliveryRecordRepository deliveryRepo,
            SupplierProfileRepository supplierRepo,
            SupplierRiskAlertService alertService) {

        this.repo = repo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
        this.alertService = alertService;
    }

    @Override
    public DelayScoreRecord computeDelayScore(Long poId) {

        PurchaseOrderRecord po = poRepo.findById(poId)
                .orElseThrow(() -> new BadRequestException("PO not found"));

        SupplierProfile supplier = supplierRepo.findById(po.getSupplierId())
                .orElseThrow(() -> new BadRequestException("Supplier not found"));

        if (supplier.getActive() != null && !supplier.getActive()) {
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

    @Override
    public List<DelayScoreRecord> getScoresBySupplier(Long supplierId) {
        return repo.findBySupplierId(supplierId);
    }

    @Override
    public List<DelayScoreRecord> getAllScores() {
        return repo.findAll();
    }
}
