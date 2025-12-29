package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
public class SupplierRiskAlertController {

    @Autowired
    private SupplierRiskAlertService riskAlertService;

    @PostMapping
    public ResponseEntity<SupplierRiskAlert> createAlert(@RequestBody SupplierRiskAlert alert) {
        SupplierRiskAlert created = riskAlertService.createAlert(alert);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<SupplierRiskAlert>> getAlertsBySupplier(@PathVariable Long supplierId) {
        List<SupplierRiskAlert> alerts = riskAlertService.getAlertsBySupplier(supplierId);
        return ResponseEntity.ok(alerts);
    }

    @PutMapping("/{alertId}/resolve")
    public ResponseEntity<SupplierRiskAlert> resolveAlert(@PathVariable Long alertId) {
        SupplierRiskAlert resolved = riskAlertService.resolveAlert(alertId);
        return ResponseEntity.ok(resolved);
    }
}