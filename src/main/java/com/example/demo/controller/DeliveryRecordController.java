package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.impl.DeliveryRecordServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordServiceImpl service;

    public DeliveryRecordController(DeliveryRecordServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryRecord record(@RequestBody DeliveryRecord delivery) {
        return service.recordDelivery(delivery);
    }

    @GetMapping
    public List<DeliveryRecord> getAll() {
        return service.getAllDeliveries();
    }

    @GetMapping("/po/{poId}")
    public List<DeliveryRecord> byPo(@PathVariable Long poId) {
        return service.getDeliveriesByPO(poId);
    }
}
