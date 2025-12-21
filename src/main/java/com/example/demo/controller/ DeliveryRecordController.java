package com.example.demo.controller;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.service.DeliveryRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryRecordController {
    private final DeliveryRecordService deliveryService;

    @PostMapping
    public ResponseEntity<DeliveryRecord> recordDelivery(@Valid @RequestBody DeliveryRecord delivery) {
        return ResponseEntity.ok(deliveryService.recordDelivery(delivery));
    }

    @GetMapping("/po/{poId}")
    public ResponseEntity<List<DeliveryRecord>> getDeliveriesByPO(@PathVariable Long poId) {
        return ResponseEntity.ok(deliveryService.getDeliveriesByPO(poId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryRecord> getDeliveryById(@PathVariable Long id) {
        return deliveryService.getDeliveryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DeliveryRecord>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }
}