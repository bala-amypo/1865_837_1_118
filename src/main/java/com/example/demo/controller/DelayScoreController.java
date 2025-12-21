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
    public ResponseEntity<DelayScoreRecord> computeDelayScore(@PathVariable Long poId) {
        return ResponseEntity.ok(delayScoreService.computeDelayScore(poId));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<DelayScoreRecord>> getScoresBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(delayScoreService.getScoresBySupplier(supplierId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DelayScoreRecord> getScoreById(@PathVariable Long id) {
        return delayScoreService.getScoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DelayScoreRecord>> getAllScores() {
        return ResponseEntity.ok(delayScoreService.getAllScores());
    }
}