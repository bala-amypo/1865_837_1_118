package com.example.demo.controller;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<PurchaseOrderRecord> create(@RequestBody PurchaseOrderRecord po) {
        return ResponseEntity.ok(purchaseOrderService.createPurchaseOrder(po));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderRecord> getById(@PathVariable Long id) {
        return purchaseOrderService.getPOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrderRecord>> getBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(purchaseOrderService.getPOsBySupplier(supplierId));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderRecord>> getAll() {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
    }
}