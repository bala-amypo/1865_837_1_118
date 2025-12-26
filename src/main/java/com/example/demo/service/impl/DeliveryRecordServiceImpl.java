package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import java.util.*;

public class DeliveryRecordServiceImpl {

    private final DeliveryRecordRepository repo;
    private final PurchaseOrderRecordRepository poRepo;

    public DeliveryRecordServiceImpl(
            DeliveryRecordRepository repo,
            PurchaseOrderRecordRepository poRepo) {
        this.repo = repo;
        this.poRepo = poRepo;
    }

    public DeliveryRecord recordDelivery(DeliveryRecord d) {
        poRepo.findById(d.getPoId())
                .orElseThrow(() -> new BadRequestException("Invalid PO id"));

        if (d.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Delivered quantity must be >=");
        }
        return repo.save(d);
    }

    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return repo.findByPoId(poId);
    }

    public List<DeliveryRecord> getAllDeliveries() {
        return repo.findAll();
    }
}
