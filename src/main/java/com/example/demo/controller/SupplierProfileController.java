package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierProfileController {

    private final SupplierService supplierService;

    public SupplierProfileController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public SupplierProfile createSupplier(@RequestBody SupplierProfile supplier) {
        return supplierService.createSupplier(supplier);
    }

    @PutMapping("/{id}/toggle")
    public SupplierProfile toggleStatus(@PathVariable Long id) {
        return supplierService.toggleStatus(id);
    }

    @GetMapping("/code/{code}")
    public SupplierProfile getByCode(@PathVariable String code) {
        return supplierService
                .findByCode(code)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }
}
