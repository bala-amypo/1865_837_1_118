package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;

import java.util.List;

public class SupplierRiskAlertServiceImpl {

    private final SupplierRiskAlertRepository repo;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository repo) {
        this.repo = repo;
    }

    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        alert.setResolved(false);
        return repo.save(alert);
    }

    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert alert = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        alert.setResolved(true);
        return repo.save(alert);
    }

    public List<SupplierRiskAlert> getAlertsBySupplier(Long id) {
        return repo.findBySupplierId(id);
    }

    public List<SupplierRiskAlert> getAllAlerts() {
        return repo.findAll();
    }
}
