package com.example.demo.controller;
import com.example.demo.model.SupplierRiskAlert;
import com.example.demo.service.SupplierRiskAlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/risk-alerts")
@RequiredArgsConstructor
public class SupplierRiskAlertController {
    private final SupplierRiskAlertService alertService;

    @PostMapping
    public ResponseEntity<SupplierRiskAlert> createAlert(@Valid @RequestBody SupplierRiskAlert alert) {
        return ResponseEntity.ok(alertService.createAlert(alert));
    }

    @PutMapping("/{id}/resolve")
    public ResponseEntity<SupplierRiskAlert> resolveAlert(@PathVariable Long id) {
        return ResponseEntity.ok(alertService.resolveAlert(id));
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<SupplierRiskAlert>> getAlertsBySupplier(@PathVariable Long supplierId) {
        return ResponseEntity.ok(alertService.getAlertsBySupplier(supplierId));
    }

    @GetMapping
    public ResponseEntity<List<SupplierRiskAlert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }
}