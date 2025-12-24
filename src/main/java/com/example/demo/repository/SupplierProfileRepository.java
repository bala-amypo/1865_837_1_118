package com.example.demo.repository;

import com.example.demo.model.SupplierProfile;
import java.util.List;
import java.util.Optional;

public interface SupplierProfileRepository {
    Optional<SupplierProfile> findById(Long id);
    SupplierProfile save(SupplierProfile s);
    List<SupplierProfile> findAll();
    Optional<SupplierProfile> findBySupplierCode(String code);
}
