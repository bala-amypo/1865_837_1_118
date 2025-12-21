package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierProfileController {

    private final SupplierProfileService supplierService;

    @PostMapping
    public ResponseEntity<SupplierProfile> createSupplier(@Valid @RequestBody SupplierProfile supplier) {
        return ResponseEntity.ok(supplierService.createSupplier(supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierProfile> updateSupplierStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(supplierService.updateSupplierStatus(id, active));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierService.getSupplierById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupplierProfile>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.getAllSuppliers());
    }

    @GetMapping("/lookup/{supplierCode}")
    public ResponseEntity<SupplierProfile> getSupplierByCode(@PathVariable String supplierCode) {
        return supplierService.getBySupplierCode(supplierCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}