package com.example.demo.service;
import com.example.demo.model.SupplierProfile;
import java.util.List;
import java.util.Optional;

public interface SupplierProfileService {
    SupplierProfile createSupplier(SupplierProfile supplier);
    SupplierProfile updateSupplierStatus(Long id, boolean active);
    SupplierProfile getSupplierById(Long id);
    Optional<SupplierProfile> getBySupplierCode(String code);
    List<SupplierProfile> getAllSuppliers();
}