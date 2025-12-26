package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryRecordServiceImpl implements DeliveryRecordService {

    private final DeliveryRecordRepository repository;
    private final PurchaseOrderRecordRepository poRepository;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository repository,
            PurchaseOrderRecordRepository poRepository) {
        this.repository = repository;
        this.poRepository = poRepository;
    }

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {

        poRepository.findById(delivery.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        if (delivery.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Delivered quantity must >=");
        }

        return repository.save(delivery);
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return repository.findByPoId(poId);
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return repository.findAll();
    }
}
