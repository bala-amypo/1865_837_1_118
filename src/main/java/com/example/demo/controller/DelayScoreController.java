package com.example.demo.controller;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.service.DelayScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delay-scores")
public class DelayScoreController {

    private final DelayScoreService delayScoreService;

    public DelayScoreController(DelayScoreService delayScoreService) {
        this.delayScoreService = delayScoreService;
    }

    @PostMapping("/compute/{poId}")
    public ResponseEntity<DelayScoreRecord> compute(@PathVariable Long poId) {
        DelayScoreRecord score = delayScoreService.computeDelayScore(poId);
        return ResponseEntity.ok(score);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<DelayScoreRecord>> getBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(delayScoreService.getScoresBySupplier(supplierId));
    }

    @GetMapping
    public ResponseEntity<List<DelayScoreRecord>> getAll() {
        return ResponseEntity.ok(delayScoreService.getAllScores());
    }
}