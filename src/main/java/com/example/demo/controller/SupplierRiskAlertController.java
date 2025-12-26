package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.impl.SupplierRiskAlertServiceImpl;
import com.example.demo.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
public class SupplierRiskAlertController {

    private final SupplierRiskAlertServiceImpl service;

    public SupplierRiskAlertController(SupplierRiskAlertServiceImpl service) {
        this.service = service;
    }

    // POST /api/risk-alerts - Create risk alert [cite: 697]
    @PostMapping
    public ResponseEntity<SupplierRiskAlert> createAlert(@Valid @RequestBody SupplierRiskAlert alert) {
        SupplierRiskAlert created = service.createAlert(alert);
        return ResponseEntity.ok(created);
    }

    // PUT /api/risk-alerts/{id}/resolve - Resolve alert [cite: 701]
    @PutMapping("/{id}/resolve")
    public ResponseEntity<SupplierRiskAlert> resolveAlert(@PathVariable Long id) {
        SupplierRiskAlert resolved = service.resolveAlert(id);
        return ResponseEntity.ok(resolved);
    }

    // GET /api/risk-alerts/supplier/{supplierId} - Get alerts for supplier [cite: 706]
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<SupplierRiskAlert>> getAlertsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(service.getAlertsBySupplier(supplierId));
    }

    // GET /api/risk-alerts/{id} - Get alert by ID [cite: 710]
    @GetMapping("/{id}")
    public ResponseEntity<SupplierRiskAlert> getAlertById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllAlerts().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found")));
    }

    // GET /api/risk-alerts - List all risk alerts [cite: 714]
    @GetMapping
    public ResponseEntity<List<SupplierRiskAlert>> getAllAlerts() {
        return ResponseEntity.ok(service.getAllAlerts());
    }
}