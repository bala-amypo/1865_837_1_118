package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "delay_score_record")
public class DelayScoreRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long poId;
    private Long supplierId;
    private Integer delayDays;
    private Double score;
    private String delaySeverity;

  
    public DelayScoreRecord() {
    }

    public DelayScoreRecord(Long poId, Long supplierId, Integer delayDays,
                            Double score, String delaySeverity) {
        this.poId = poId;
        this.supplierId = supplierId;
        this.delayDays = delayDays;
        this.score = score;
        this.delaySeverity = delaySeverity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPoId() { return poId; }
    public void setPoId(Long poId) { this.poId = poId; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public Integer getDelayDays() { return delayDays; }
    public void setDelayDays(Integer delayDays) { this.delayDays = delayDays; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public String getDelaySeverity() { return delaySeverity; }
    public void setDelaySeverity(String delaySeverity) { this.delaySeverity = delaySeverity; }
}
