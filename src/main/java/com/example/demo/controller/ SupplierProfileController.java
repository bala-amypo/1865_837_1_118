package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierProfileController {

    private final SupplierProfileService supplierProfileService;

    @PostMapping
    public ResponseEntity<SupplierProfile> create(@RequestBody SupplierProfile supplier) {
        return ResponseEntity.ok(supplierProfileService.createSupplier(supplier));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getById(@PathVariable Long id) {
        return ResponseEntity.ok(supplierProfileService.getSupplierById(id));
    }

    @GetMapping
    public ResponseEntity<List<SupplierProfile>> getAll() {
        return ResponseEntity.ok(supplierProfileService.getAllSuppliers());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SupplierProfile> updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(supplierProfileService.updateSupplierStatus(id, active));
    }
}