// package com.example.demo.controller;

// import com.example.demo.model.PurchaseOrderRecord;
// import com.example.demo.service.PurchaseOrderService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/purchase-orders")
// @RequiredArgsConstructor
// public class PurchaseOrderController {
//     private final PurchaseOrderService service;

//     @PostMapping
//     public PurchaseOrderRecord create(@RequestBody PurchaseOrderRecord po) {
//         return service.createPurchaseOrder(po);
//     }

//     @GetMapping
//     public List<PurchaseOrderRecord> getAll() {
//         return service.getAllPurchaseOrders();
//     }

//     @GetMapping("/{id}")
//     public PurchaseOrderRecord getById(@PathVariable Long id) {
//         return service.getPOById(id).orElseThrow(() -> new RuntimeException("PO Not found"));
//     }

//     @GetMapping("/supplier/{supplierId}")
//     public List<PurchaseOrderRecord> getBySupplier(@PathVariable Long supplierId) {
//         return service.getPOsBySupplier(supplierId);
//     }
// }