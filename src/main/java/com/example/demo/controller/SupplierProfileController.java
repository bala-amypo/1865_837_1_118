package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.impl.SupplierProfileServiceImpl;
import com.example.demo.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierProfileController {

    private final SupplierProfileServiceImpl service;

    // Constructor-based dependency injection
    public SupplierProfileController(SupplierProfileServiceImpl service) {
        this.service = service;
    }

    // POST /api/suppliers - Create new supplier
    @PostMapping
    public ResponseEntity<SupplierProfile> createSupplier(@Valid @RequestBody SupplierProfile supplier) {
        SupplierProfile created = service.createSupplier(supplier);
        return ResponseEntity.ok(created);
    }

    // PUT /api/suppliers/{id} - Update supplier status
    @PutMapping("/{id}")
    public ResponseEntity<SupplierProfile> updateSupplierStatus(
            @PathVariable Long id, 
            @RequestParam boolean active) {
        SupplierProfile updated = service.updateSupplierStatus(id, active);
        return ResponseEntity.ok(updated);
    }

    // GET /api/suppliers/{id} - Get supplier by ID
    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getSupplierById(@PathVariable Long id) {
        SupplierProfile supplier = service.getSupplierById(id);
        return ResponseEntity.ok(supplier);
    }

    // GET /api/suppliers - List all suppliers
    @GetMapping
    public ResponseEntity<List<SupplierProfile>> getAllSuppliers() {
        return ResponseEntity.ok(service.getAllSuppliers());
    }

    // GET /api/suppliers/lookup/{supplierCode} - Lookup supplier by code
    @GetMapping("/lookup/{supplierCode}")
    public ResponseEntity<SupplierProfile> getBySupplierCode(@PathVariable String supplierCode) {
        return service.getBySupplierCode(supplierCode)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with code: " + supplierCode));
    }
}