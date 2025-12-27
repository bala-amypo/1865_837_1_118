package com.example.demo.controller;

import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.impl.SupplierRiskAlertServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
public class SupplierRiskAlertController {

    private final SupplierRiskAlertServiceImpl service;

    public SupplierRiskAlertController(SupplierRiskAlertServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public SupplierRiskAlert create(@RequestBody SupplierRiskAlert alert) {
        return service.createAlert(alert);
    }

    @PutMapping("/{id}/resolve")
    public SupplierRiskAlert resolve(@PathVariable Long id) {
        return service.resolveAlert(id);
    }

    @GetMapping
    public List<SupplierRiskAlert> getAll() {
        return service.getAllAlerts();
    }

    @GetMapping("/supplier/{supplierId}")
    public List<SupplierRiskAlert> bySupplier(@PathVariable Long supplierId) {
        return service.getAlertsBySupplier(supplierId);
    }
}
