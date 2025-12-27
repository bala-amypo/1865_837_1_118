package com.example.demo.service;

import com.example.demo.model.SupplierProfile;
import java.util.List;
import java.util.Optional;

public interface SupplierService {

    SupplierProfile createSupplier(SupplierProfile supplier);

    SupplierProfile toggleStatus(Long supplierId);

    Optional<SupplierProfile> findByCode(String supplierCode);

    List<SupplierProfile> findAll();
}
