package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryRecordServiceImpl {

    private final DeliveryRecordRepository deliveryRepository;
    private final PurchaseOrderRecordRepository poRepository;

    public DeliveryRecordServiceImpl(DeliveryRecordRepository deliveryRepository, 
                                   PurchaseOrderRecordRepository poRepository) {
        this.deliveryRepository = deliveryRepository;
        this.poRepository = poRepository;
    }

    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {
        // Test: testRecordDelivery_invalidPo
        poRepository.findById(delivery.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        // Test: testRecordDelivery_negativeQuantity
        if (delivery.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Delivered quantity must be >=");
        }

        return deliveryRepository.save(delivery);
    }

    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveryRepository.findByPoId(poId);
    }

    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}