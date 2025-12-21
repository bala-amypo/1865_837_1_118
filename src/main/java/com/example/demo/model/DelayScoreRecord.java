package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delay_score_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"po_id"})
})
public class DelayScoreRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "po_id", nullable = false, unique = true)
    private Long poId;

    @Column(name = "delay_days", nullable = false)
    private Integer delayDays;

    @Column(name = "delay_severity", nullable = false)
    private String delaySeverity; // ON_TIME, MINOR, MODERATE, SEVERE

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "computed_at", nullable = false)
    private LocalDateTime computedAt;

    public DelayScoreRecord() {
    }

    public DelayScoreRecord(Long supplierId, Long poId, Integer delayDays, String delaySeverity, Double score) {
        this.supplierId = supplierId;
        this.poId = poId;
        this.delayDays = delayDays;
        this.delaySeverity = delaySeverity;
        this.score = score;
    }

    @PrePersist
    public void prePersist() {
        this.computedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public Long getPoId() { return poId; }
    public void setPoId(Long poId) { this.poId = poId; }
    public Integer getDelayDays() { return delayDays; }
    public void setDelayDays(Integer delayDays) { this.delayDays = delayDays; }
    public String getDelaySeverity() { return delaySeverity; }
    public void setDelaySeverity(String delaySeverity) { this.delaySeverity = delaySeverity; }
    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }
    public LocalDateTime getComputedAt() { return computedAt; }
}