package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
public class SupplierRiskAlertController {

    private final SupplierRiskAlertService supplierRiskAlertService;

    public SupplierRiskAlertController(SupplierRiskAlertService supplierRiskAlertService) {
        this.supplierRiskAlertService = supplierRiskAlertService;
    }

    @PostMapping
    public ResponseEntity<SupplierRiskAlert> create(@RequestBody SupplierRiskAlert alert) {
        SupplierRiskAlert created = supplierRiskAlertService.createAlert(alert);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<SupplierRiskAlert>> getBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(supplierRiskAlertService.getAlertsBySupplier(supplierId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<SupplierRiskAlert> resolve(@PathVariable Long id) {
        SupplierRiskAlert resolved = supplierRiskAlertService.resolveAlert(id);
        return ResponseEntity.ok(resolved);
    }

    @GetMapping
    public ResponseEntity<List<SupplierRiskAlert>> getAll() {
        return ResponseEntity.ok(supplierRiskAlertService.getAllAlerts());
    }
}