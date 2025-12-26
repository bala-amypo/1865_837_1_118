package com.example.demo.controller;

import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.impl.DeliveryRecordServiceImpl;
import com.example.demo.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryRecordController {

    private final DeliveryRecordServiceImpl service;

    public DeliveryRecordController(DeliveryRecordServiceImpl service) {
        this.service = service;
    }

    // POST /api/deliveries - Record delivery [cite: 655]
    @PostMapping
    public ResponseEntity<DeliveryRecord> recordDelivery(@Valid @RequestBody DeliveryRecord delivery) {
        DeliveryRecord created = service.recordDelivery(delivery);
        return ResponseEntity.ok(created);
    }

    // GET /api/deliveries/po/{poId} - Get deliveries for PO [cite: 660]
    @GetMapping("/po/{poId}")
    public ResponseEntity<List<DeliveryRecord>> getDeliveriesByPO(@PathVariable Long poId) {
        return ResponseEntity.ok(service.getDeliveriesByPO(poId));
    }

    // GET /api/deliveries/{id} - Get delivery by ID [cite: 664]
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRecord> getDeliveryById(@PathVariable Long id) {
        // Assuming service has getById method, implementing based on typical pattern
        // If specific method is missing in service impl, use repository direct or add to service
        // For this response, assuming standard service pattern from SRS
         return ResponseEntity.ok(service.getAllDeliveries().stream()
                 .filter(d -> d.getId().equals(id))
                 .findFirst()
                 .orElseThrow(() -> new ResourceNotFoundException("Delivery not found")));
    }

    // GET /api/deliveries - List all deliveries [cite: 669]
    @GetMapping
    public ResponseEntity<List<DeliveryRecord>> getAllDeliveries() {
        return ResponseEntity.ok(service.getAllDeliveries());
    }
}