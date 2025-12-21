package com.example.demo.controller;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.service.DelayScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delay-scores")
@RequiredArgsConstructor
public class DelayScoreController {

    private final DelayScoreService delayScoreService;

    @PostMapping("/compute/{poId}")
    public ResponseEntity<DelayScoreRecord> compute(@PathVariable Long poId) {
        return ResponseEntity.ok(delayScoreService.computeDelayScore(poId));
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