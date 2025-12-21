package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
@RequiredArgsConstructor
public class SupplierRiskAlertController {

    private final SupplierRiskAlertService supplierRiskAlertService;

    @PostMapping
    public ResponseEntity<SupplierRiskAlert> create(@RequestBody SupplierRiskAlert alert) {
        return ResponseEntity.ok(supplierRiskAlertService.createAlert(alert));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<SupplierRiskAlert>> getBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(supplierRiskAlertService.getAlertsBySupplier(supplierId));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<SupplierRiskAlert> resolve(@PathVariable Long id) {
        return ResponseEntity.ok(supplierRiskAlertService.resolveAlert(id));
    }

    @GetMapping
    public ResponseEntity<List<SupplierRiskAlert>> getAll() {
        return ResponseEntity.ok(supplierRiskAlertService.getAllAlerts());
    }
}