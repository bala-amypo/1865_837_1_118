// package com.example.demo.controller;

// import com.example.demo.model.DeliveryRecord;
// import com.example.demo.service.DeliveryRecordService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/deliveries")
// @RequiredArgsConstructor
// public class DeliveryRecordController {
//     private final DeliveryRecordService service;

//     @PostMapping
//     public DeliveryRecord create(@RequestBody DeliveryRecord d) {
//         return service.recordDelivery(d);
//     }

//     @GetMapping("/po/{poId}")
//     public List<DeliveryRecord> getByPO(@PathVariable Long poId) {
//         return service.getDeliveriesByPO(poId);
//     }

//     @GetMapping
//     public List<DeliveryRecord> getAll() {
//         return service.getAllDeliveries();
//     }
// }