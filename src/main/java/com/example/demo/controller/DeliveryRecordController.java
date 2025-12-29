package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    @Autowired
    private DeliveryRecordService deliveryRecordService;

    @PostMapping
    public ResponseEntity<DeliveryRecord> recordDelivery(@RequestBody DeliveryRecord delivery) {
        DeliveryRecord recorded = deliveryRecordService.recordDelivery(delivery);
        return ResponseEntity.ok(recorded);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryRecord>> getAllDeliveries() {
        List<DeliveryRecord> deliveries = deliveryRecordService.getAllDeliveries();
        return ResponseEntity.ok(deliveries);
    }

    @GetMapping("/po/{poId}")
    public ResponseEntity<List<DeliveryRecord>> getDeliveriesByPO(@PathVariable Long poId) {
        List<DeliveryRecord> deliveries = deliveryRecordService.getDeliveriesByPO(poId);
        return ResponseEntity.ok(deliveries);
    }
}