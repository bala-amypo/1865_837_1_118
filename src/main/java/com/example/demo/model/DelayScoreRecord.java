package com.example.demo.model;

public class DelayScoreRecord {
    private Long id;
    private Long poId;
    private Long supplierId;
    private Integer delayDays;
    private Double score;
    private String delaySeverity;

    public Long getPoId() { return poId; }
    public void setPoId(Long poId) { this.poId = poId; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public Integer getDelayDays() { return delayDays; }
    public void setDelayDays(Integer delayDays) { this.delayDays = delayDays; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public String getDelaySeverity() { return delaySeverity; }
    public void setDelaySeverity(String delaySeverity) {
        this.delaySeverity = delaySeverity;
    }
}
