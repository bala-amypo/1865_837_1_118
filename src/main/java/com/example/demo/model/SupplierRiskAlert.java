package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "supplier_risk_alerts")
public class SupplierRiskAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    private String alertLevel;
    private String message;
    private LocalDateTime alertDate;
    private Boolean resolved = false;

    public SupplierRiskAlert() {}

    @PrePersist
    public void onCreate() {
        this.alertDate = LocalDateTime.now();
        if(this.resolved == null) this.resolved = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public String getAlertLevel() { return alertLevel; }
    public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getAlertDate() { return alertDate; }
    public void setAlertDate(LocalDateTime alertDate) { this.alertDate = alertDate; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierRiskAlert that = (SupplierRiskAlert) o;
        return Objects.equals(id, that.id) && 
               Objects.equals(supplierId, that.supplierId) && 
               Objects.equals(alertLevel, that.alertLevel) && 
               Objects.equals(message, that.message) && 
               Objects.equals(resolved, that.resolved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, supplierId, alertLevel, message, resolved);
    }
}