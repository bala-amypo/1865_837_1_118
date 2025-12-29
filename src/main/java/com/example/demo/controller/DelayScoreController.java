package com.example.demo.controller;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.service.DelayScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delay-scores")
public class DelayScoreController {

    @Autowired
    private DelayScoreService delayScoreService;

    @PostMapping("/compute/{poId}")
    public ResponseEntity<DelayScoreRecord> computeScore(@PathVariable Long poId) {
        DelayScoreRecord score = delayScoreService.computeDelayScore(poId);
        return ResponseEntity.ok(score);
    }

    @GetMapping
    public ResponseEntity<List<DelayScoreRecord>> getAllScores() {
        List<DelayScoreRecord> scores = delayScoreService.getAllScores();
        return ResponseEntity.ok(scores);
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<DelayScoreRecord>> getScoresBySupplier(@PathVariable Long supplierId) {
        List<DelayScoreRecord> scores = delayScoreService.getScoresBySupplier(supplierId);
        return ResponseEntity.ok(scores);
    }
}