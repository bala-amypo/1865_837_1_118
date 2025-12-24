package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;

import java.util.List;

public class DeliveryRecordServiceImpl {
    private final DeliveryRecordRepository deliveryRepo;
    private final PurchaseOrderRecordRepository poRepo;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRepo,
                                     PurchaseOrderRecordRepository poRepo) {
        this.deliveryRepo = deliveryRepo;
        this.poRepo = poRepo;
    }

    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {
        poRepo.findById(delivery.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));
        if (delivery.getDeliveredQuantity() == null || delivery.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Delivered quantity must be >= 0");
        }
        return deliveryRepo.save(delivery);
    }

    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveryRepo.findByPoId(poId);
    }

    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryRepo.findAll();
    }
}
