package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;

import java.util.List;

public class SupplierRiskAlertServiceImpl {

    SupplierRiskAlertRepository riskAlertRepository;

    public SupplierRiskAlertServiceImpl() {
    }

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository repo) {
        this.riskAlertRepository = repo;
    }

    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        alert.setResolved(false);
        return riskAlertRepository.save(alert);
    }

    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert alert = riskAlertRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        alert.setResolved(true);
        return riskAlertRepository.save(alert);
    }

    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return riskAlertRepository.findBySupplierId(supplierId);
    }

    public List<SupplierRiskAlert> getAllAlerts() {
        return riskAlertRepository.findAll();
    }
}
