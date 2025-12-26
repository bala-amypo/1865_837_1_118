package com.example.demo.controller;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.impl.PurchaseOrderServiceImpl;
import com.example.demo.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderServiceImpl service;

    public PurchaseOrderController(PurchaseOrderServiceImpl service) {
        this.service = service;
    }

    // POST /api/purchase-orders - Create purchase order [cite: 634]
    @PostMapping
    public ResponseEntity<PurchaseOrderRecord> createPurchaseOrder(@Valid @RequestBody PurchaseOrderRecord po) {
        PurchaseOrderRecord created = service.createPurchaseOrder(po);
        return ResponseEntity.ok(created);
    }

    // GET /api/purchase-orders/supplier/{supplierId} - Get POs for supplier [cite: 639]
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrderRecord>> getPOsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(service.getPOsBySupplier(supplierId));
    }

    // GET /api/purchase-orders/{id} - Get PO by ID [cite: 643]
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderRecord> getPOById(@PathVariable Long id) {
        return service.getPOById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase Order not found"));
    }

    // GET /api/purchase-orders - List all POs [cite: 647]
    @GetMapping
    public ResponseEntity<List<PurchaseOrderRecord>> getAllPurchaseOrders() {
        return ResponseEntity.ok(service.getAllPurchaseOrders());
    }
}