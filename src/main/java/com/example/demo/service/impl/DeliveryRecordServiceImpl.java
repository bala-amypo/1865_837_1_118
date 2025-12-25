package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;

import java.util.List;
import java.util.Optional;

public class DeliveryRecordServiceImpl {

    private final DeliveryRecordRepository deliveryRepo;
    private final PurchaseOrderRecordRepository poRepo;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository deliveryRepo,
            PurchaseOrderRecordRepository poRepo) {
        this.deliveryRepo = deliveryRepo;
        this.poRepo = poRepo;
    }

    // ----------------------------
    // record delivery
    // ----------------------------
    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {

        if (delivery.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Delivered quantity must be >=");
        }

        poRepo.findById(delivery.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        return deliveryRepo.save(delivery);
    }

    // ----------------------------
    // queries
    // ----------------------------
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveryRepo.findByPoId(poId);
    }

    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return deliveryRepo.findById(id);
    }

    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryRepo.findAll();
    }
}
