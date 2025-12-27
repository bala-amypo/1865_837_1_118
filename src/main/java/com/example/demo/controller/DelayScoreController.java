// package com.example.demo.controller;

// import com.example.demo.model.DelayScoreRecord;
// import com.example.demo.service.DelayScoreService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/api/delay-scores")
// @RequiredArgsConstructor
// public class DelayScoreController {
//     private final DelayScoreService service;

//     @PostMapping("/compute/{poId}")
//     public DelayScoreRecord compute(@PathVariable Long poId) {
//         return service.computeDelayScore(poId);
//     }

//     @GetMapping("/supplier/{supplierId}")
//     public List<DelayScoreRecord> getBySupplier(@PathVariable Long supplierId) {
//         return service.getScoresBySupplier(supplierId);
//     }

//     @GetMapping
//     public List<DelayScoreRecord> getAll() {
//         return service.getAllScores();
//     }
// }