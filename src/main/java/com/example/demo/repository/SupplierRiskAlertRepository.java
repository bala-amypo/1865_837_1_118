package com.example.demo.repository;

import com.example.demo.model.SupplierRiskAlert;
import java.util.List;
import java.util.Optional;

public interface SupplierRiskAlertRepository {
    SupplierRiskAlert save(SupplierRiskAlert a);
    Optional<SupplierRiskAlert> findById(Long id);
    List<SupplierRiskAlert> findBySupplierId(Long supplierId);
    List<SupplierRiskAlert> findAll();
}
