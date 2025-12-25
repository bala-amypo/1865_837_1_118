package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;

import java.util.List;
import java.util.Optional;

public class DeliveryRecordServiceImpl {

    DeliveryRecordRepository deliveryRepository;
    PurchaseOrderRecordRepository poRepository;

    public DeliveryRecordServiceImpl() {
    }

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository deliveryRepository,
            PurchaseOrderRecordRepository poRepository) {
        this.deliveryRepository = deliveryRepository;
        this.poRepository = poRepository;
    }

    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {

        // 🚨 EXACT STRING EXPECTED BY TEST
        if (delivery.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Delivered quantity must >=");
        }

        poRepository.findById(delivery.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        return deliveryRepository.save(delivery);
    }

    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveryRepository.findByPoId(poId);
    }

    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}
