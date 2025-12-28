package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.repository.SupplierRiskAlertRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierRiskAlertServiceImpl {
    private final SupplierRiskAlertRepository repository;

    public SupplierRiskAlertServiceImpl(SupplierRiskAlertRepository repository) {
        this.repository = repository;
    }

    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        alert.setResolved(false);
        return repository.save(alert);
    }

    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return repository.findBySupplierId(supplierId);
    }

    public SupplierRiskAlert resolveAlert(Long alertId) {
        SupplierRiskAlert alert = repository.findById(alertId)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        alert.setResolved(true);
        return repository.save(alert);
    }

    public List<SupplierRiskAlert> getAllAlerts() {
        return repository.findAll();
    }
}