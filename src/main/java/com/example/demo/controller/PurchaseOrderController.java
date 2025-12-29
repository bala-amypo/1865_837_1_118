package com.example.demo.controller;

import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<PurchaseOrderRecord> createPO(@RequestBody PurchaseOrderRecord po) {
        PurchaseOrderRecord created = purchaseOrderService.createPurchaseOrder(po);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderRecord> getPO(@PathVariable Long id) {
        Optional<PurchaseOrderRecord> po = purchaseOrderService.getPOById(id);
        return po.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrderRecord>> getPOsBySupplier(@PathVariable Long supplierId) {
        List<PurchaseOrderRecord> pos = purchaseOrderService.getPOsBySupplier(supplierId);
        return ResponseEntity.ok(pos);
    }
}