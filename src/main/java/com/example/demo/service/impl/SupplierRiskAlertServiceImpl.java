package com.example.demo.service.impl;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRiskAlertRepository;
import com.example.demo.service.SupplierRiskAlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierRiskAlertServiceImpl implements SupplierRiskAlertService {
    private final SupplierRiskAlertRepository repository;

    @Override
    public SupplierRiskAlert createAlert(SupplierRiskAlert alert) {
        return repository.save(alert);
    }

    @Override
    public SupplierRiskAlert resolveAlert(Long id) {
        SupplierRiskAlert alert = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alert not found"));
        alert.setResolved(true);
        return repository.save(alert);
    }

    @Override
    public List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId) {
        return repository.findBySupplierId(supplierId);
    }

    @Override
    public List<SupplierRiskAlert> getAllAlerts() {
        return repository.findAll();
    }
}