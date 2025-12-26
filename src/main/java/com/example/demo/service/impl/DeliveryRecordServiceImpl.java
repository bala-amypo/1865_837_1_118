package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // ✅ THIS IS CRITICAL
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository deliveryRecordRepository;
    private final PurchaseOrderRecordRepository purchaseOrderRecordRepository;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository deliveryRecordRepository,
            PurchaseOrderRecordRepository purchaseOrderRecordRepository) {

        this.deliveryRecordRepository = deliveryRecordRepository;
        this.purchaseOrderRecordRepository = purchaseOrderRecordRepository;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord deliveryRecord) {

        PurchaseOrderRecord po = purchaseOrderRecordRepository
                .findById(deliveryRecord.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        if (deliveryRecord.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Delivered quantity must be >=");
        }

        return deliveryRecordRepository.save(deliveryRecord);
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveryRecordRepository.findByPoId(poId);
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryRecordRepository.findAll();
    }
}
