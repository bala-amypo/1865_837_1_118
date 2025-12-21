package com.example.demo.controller;
import com.example.demo.model.PurchaseOrderRecord;
import com.example.demo.service.PurchaseOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {
    private final PurchaseOrderService poService;

    @PostMapping
    public ResponseEntity<PurchaseOrderRecord> createPO(@Valid @RequestBody PurchaseOrderRecord po) {
        return ResponseEntity.ok(poService.createPurchaseOrder(po));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<PurchaseOrderRecord>> getPOsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(poService.getPOsBySupplier(supplierId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderRecord> getPOById(@PathVariable Long id) {
        return poService.getPOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderRecord>> getAllPOs() {
        return ResponseEntity.ok(poService.getAllPurchaseOrders());
    }
}