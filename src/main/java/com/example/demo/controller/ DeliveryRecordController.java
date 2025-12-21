package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordService deliveryRecordService;

    public DeliveryRecordController(DeliveryRecordService deliveryRecordService) {
        this.deliveryRecordService = deliveryRecordService;
    }

    @PostMapping
    public ResponseEntity<DeliveryRecord> record(@RequestBody DeliveryRecord delivery) {
        DeliveryRecord saved = deliveryRecordService.recordDelivery(delivery);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/po/{poId}")
    public ResponseEntity<List<DeliveryRecord>> getByPo(@PathVariable Long poId) {
        return ResponseEntity.ok(deliveryRecordService.getDeliveriesByPO(poId));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryRecord>> getAll() {
        return ResponseEntity.ok(deliveryRecordService.getAllDeliveries());
    }
}