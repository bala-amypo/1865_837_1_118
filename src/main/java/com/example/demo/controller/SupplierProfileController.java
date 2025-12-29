package com.example.demo.controller;

import com.example.demo.model.SupplierProfile;
import com.example.demo.service.impl.SupplierProfileServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@Tag(name = "Supplier Management")
public class SupplierProfileController {
    private final SupplierProfileServiceImpl service;

    public SupplierProfileController(SupplierProfileServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public SupplierProfile create(@RequestBody SupplierProfile s) {
        return service.createSupplier(s);
    }

    @GetMapping
    public List<SupplierProfile> getAll() {
        return service.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public SupplierProfile getById(@PathVariable Long id) {
        return service.getSupplierById(id);
    }

    @PutMapping("/{id}/status")
    public SupplierProfile updateStatus(@PathVariable Long id, @RequestParam boolean active) {
        return service.updateSupplierStatus(id, active);
    }
}