package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierProfileController {

    @Autowired
    private SupplierProfileService supplierProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getSupplier(@PathVariable Long id) {
        SupplierProfile supplier = supplierProfileService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping
    public ResponseEntity<List<SupplierProfile>> getAllSuppliers() {
        List<SupplierProfile> suppliers = supplierProfileService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @PostMapping
    public ResponseEntity<SupplierProfile> createSupplier(@RequestBody SupplierProfile supplier) {
        SupplierProfile created = supplierProfileService.createSupplier(supplier);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SupplierProfile> updateStatus(@PathVariable Long id, @RequestParam Boolean active) {
        SupplierProfile updated = supplierProfileService.updateSupplierStatus(id, active);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<SupplierProfile> getByCode(@PathVariable String code) {
        Optional<SupplierProfile> supplier = supplierProfileService.getBySupplierCode(code);
        return supplier.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}