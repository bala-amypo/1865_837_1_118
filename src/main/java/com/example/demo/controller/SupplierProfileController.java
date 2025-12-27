package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
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
    public SupplierProfile toggleSupplier(@PathVariable Long id) {
        return supplierService.toggleStatus(id);
    }

    @GetMapping
    public List<SupplierProfile> getAllSuppliers() {
        return supplierService.findAll();
    }

    @GetMapping("/code/{code}")
    public SupplierProfile getByCode(@PathVariable String code) {
        return supplierService.findByCode(code).orElse(null);
    }
}
