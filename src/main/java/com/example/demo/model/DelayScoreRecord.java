package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "delay_score_records")
public class DelayScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    
    @Column(unique = true)
    private Long poId;
    
    private Integer delayDays;
    private String delaySeverity; 
    private Double score;
    private LocalDateTime computedAt;

    public DelayScoreRecord() {}

    @PrePersist
    public void onCreate() {
        this.computedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public void setComputedAt(LocalDateTime computedAt) { this.computedAt = computedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DelayScoreRecord that = (DelayScoreRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(poId, that.poId) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, poId, score);
    }
    
    @Override
    public String toString() {
        return "DelayScoreRecord{id=" + id + ", poId=" + poId + ", score=" + score + '}';
    }
}