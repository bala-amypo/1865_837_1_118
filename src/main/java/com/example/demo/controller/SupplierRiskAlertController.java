package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
@RequiredArgsConstructor
public class SupplierRiskAlertController {
    private final SupplierRiskAlertService service;

    @PostMapping
    public SupplierRiskAlert create(@RequestBody SupplierRiskAlert alert) {
        return service.createAlert(alert);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<SupplierRiskAlert> getBySupplier(@PathVariable Long supplierId) {
        return service.getAlertsBySupplier(supplierId);
    }

    @PutMapping("/{id}/resolve")
    public SupplierRiskAlert resolve(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    @GetMapping
    public List<SupplierRiskAlert> getAll() {
        return service.getAllAlerts();
    }
}