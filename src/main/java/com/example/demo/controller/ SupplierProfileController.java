package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierProfileController {

    private final SupplierProfileService supplierProfileService;

    public SupplierProfileController(SupplierProfileService supplierProfileService) {
        this.supplierProfileService = supplierProfileService;
    }

    @PostMapping
    public ResponseEntity<SupplierProfile> create(@RequestBody SupplierProfile supplier) {
        SupplierProfile created = supplierProfileService.createSupplier(supplier);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getById(@PathVariable Long id) {
        SupplierProfile supplier = supplierProfileService.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping
    public ResponseEntity<List<SupplierProfile>> getAll() {
        return ResponseEntity.ok(supplierProfileService.getAllSuppliers());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SupplierProfile> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        SupplierProfile updated = supplierProfileService.updateSupplierStatus(id, active);
        return ResponseEntity.ok(updated);
    }
}