package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;

import java.util.List;

public class SupplierRiskAlertServiceImpl {
    private final SupplierRiskAlertRepository alertRepo;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository alertRepo) {
        this.alertRepo = alertRepo;
    }

    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        alert.setResolved(false);
        alertRepo.save(alert);
        return alert;
    }

    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert a = alertRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        a.setResolved(true);
        alertRepo.save(a);
        return a;
    }

    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return alertRepo.findBySupplierId(supplierId);
    }

    public List<SupplierRiskAlert> getAllAlerts() {
        return alertRepo.findAll();
    }
}
