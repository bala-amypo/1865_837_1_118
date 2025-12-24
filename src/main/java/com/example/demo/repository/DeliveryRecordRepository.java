package com.example.demo.repository;

import com.example.demo.model.DeliveryRecord;
import java.util.List;

public interface DeliveryRecordRepository {
    DeliveryRecord save(DeliveryRecord d);
    List<DeliveryRecord> findByPoId(Long poId);
    List<DeliveryRecord> findAll();
}
