package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.impl.DeliveryRecordServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryRecordServiceImpl service;

    public DeliveryController(DeliveryRecordServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public DeliveryRecord record(@RequestBody DeliveryRecord delivery) {
        return service.recordDelivery(delivery);
    }

    @GetMapping("/po/{poId}")
    public List<DeliveryRecord> byPo(@PathVariable Long poId) {
        return service.getDeliveriesByPO(poId);
    }

    @GetMapping
    public List<DeliveryRecord> all() {
        return service.getAllDeliveries();
    }
}
