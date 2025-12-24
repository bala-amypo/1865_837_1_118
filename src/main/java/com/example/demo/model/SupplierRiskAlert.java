package com.example.demo.model;

public class SupplierRiskAlert {
    private Long id;
    private Long supplierId;
    private String alertLevel;
    private String message;
    private Boolean resolved = false;

    public SupplierRiskAlert() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public String getAlertLevel() { return alertLevel; }
    public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
}
