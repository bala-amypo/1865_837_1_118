package com.example.demo.controller;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.service.impl.DelayScoreServiceImpl;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delay-scores")
public class DelayScoreController {

    private final DelayScoreServiceImpl service;

    public DelayScoreController(DelayScoreServiceImpl service) {
        this.service = service;
    }

    // POST /api/delay-scores/compute/{poId} - Compute delay score for PO [cite: 677]
    @PostMapping("/compute/{poId}")
    public ResponseEntity<DelayScoreRecord> computeDelayScore(@PathVariable Long poId) {
        DelayScoreRecord score = service.computeDelayScore(poId);
        return ResponseEntity.ok(score);
    }

    // GET /api/delay-scores/supplier/{supplierId} - Get scores for supplier [cite: 681]
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<DelayScoreRecord>> getScoresBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(service.getScoresBySupplier(supplierId));
    }

    // GET /api/delay-scores/{id} - Get score by ID [cite: 685]
    @GetMapping("/{id}")
    public ResponseEntity<DelayScoreRecord> getScoreById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAllScores().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Delay score not found")));
    }

    // GET /api/delay-scores - List all delay scores [cite: 689]
    @GetMapping
    public ResponseEntity<List<DelayScoreRecord>> getAllScores() {
        return ResponseEntity.ok(service.getAllScores());
    }
}