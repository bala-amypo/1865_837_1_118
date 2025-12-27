package com.example.demo.service;

import com.example.demo.model.SupplierProfile;
import java.util.List;

public interface SupplierService {

    SupplierProfile createSupplier(SupplierProfile supplier);

    SupplierProfile toggleStatus(Long supplierId);

    SupplierProfile findByCode(String supplierCode);

    List<SupplierProfile> findAll();
}
