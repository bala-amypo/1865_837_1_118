package com.example.demo.repository;

import com.example.demo.model.DeliveryRecord;
import java.util.List;
import java.util.Optional;

public interface DeliveryRecordRepository {

    // used in recordDelivery()
    DeliveryRecord save(Object o);

    // 🔴 REQUIRED for getDeliveryById()
    Optional<DeliveryRecord> findById(Long id);

    // used in getDeliveriesByPO()
    List<DeliveryRecord> findByPoId(Long poId);

    // used in criteria tests
    List<DeliveryRecord> findAll();
}
