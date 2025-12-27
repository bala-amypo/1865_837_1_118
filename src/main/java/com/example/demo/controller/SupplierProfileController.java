package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierProfileController {

    private final SupplierProfileService service;

    public SupplierProfileController(SupplierProfileService service) {
        this.service = service;
    }

    // ---- BASIC GET BY ID ----
    @GetMapping("/{id}")
    public ResponseEntity<SupplierProfile> getSupplierById(@PathVariable Long id) {
        SupplierProfile supplier = service.getSupplierById(id);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplier);
    }

    // ---- CREATE SUPPLIER ----
    @PostMapping
    public SupplierProfile createSupplier(@RequestBody SupplierProfile supplier) {
        return service.createSupplier(supplier);
    }

    // ---- TOGGLE ACTIVE STATUS ----
    @PutMapping("/{id}/toggle")
    public ResponseEntity<SupplierProfile> toggleSupplierStatus(@PathVariable Long id) {
        SupplierProfile supplier = service.getSupplierById(id);
        if (supplier == null) {
            return ResponseEntity.notFound().build();
        }
        SupplierProfile updated =
                service.updateSupplierStatus(id, !supplier.getActive());
        return ResponseEntity.ok(updated);
    }

    // ---- LOOKUP BY SUPPLIER CODE ----
    @GetMapping("/code/{code}")
    public ResponseEntity<SupplierProfile> getBySupplierCode(@PathVariable String code) {
        Optional<SupplierProfile> supplierOpt = service.getBySupplierCode(code);
        return supplierOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
