package com.example.demo.controller;

import com.example.demo.model.DelayScoreRecord;
import com.example.demo.service.impl.DelayScoreServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delay-scores")
public class DelayScoreController {

    private final DelayScoreServiceImpl service;

    public DelayScoreController(DelayScoreServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/{poId}")
    public DelayScoreRecord compute(@PathVariable Long poId) {
        return service.computeDelayScore(poId);
    }

    @GetMapping("/supplier/{supplierId}")
    public List<DelayScoreRecord> bySupplier(@PathVariable Long supplierId) {
        return service.getScoresBySupplier(supplierId);
    }

    @GetMapping
    public List<DelayScoreRecord> all() {
        return service.getAllScores();
    }
}
