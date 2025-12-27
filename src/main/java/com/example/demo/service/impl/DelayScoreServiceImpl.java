package com.example.demo.service.impl;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.repository.DelayScoreRecordRepository;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.repository.SupplierProfileRepository;
import com.example.demo.service.DelayScoreService;
import org.springframework.stereotype.Service;

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
            SupplierProfileRepository supplierRepo) {

        this.scoreRepo = scoreRepo;
        this.poRepo = poRepo;
        this.deliveryRepo = deliveryRepo;
        this.supplierRepo = supplierRepo;
    }

    @Override
    public DelayScoreRecord save(DelayScoreRecord record) {
        return scoreRepo.save(record);
    }

    @Override
    public List<DelayScoreRecord> findAll() {
        return scoreRepo.findAll();
    }
}
