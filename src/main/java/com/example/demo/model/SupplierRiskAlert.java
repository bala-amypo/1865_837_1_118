// package com.example.demo.model;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "supplier_risk_alert")
// public class SupplierRiskAlert {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private Long supplierId;
//     private String alertType;
//     private String description;
//     private Boolean resolved = false;
//     private String alertLevel;

//     public SupplierRiskAlert() {
//     }

//     public SupplierRiskAlert(Long supplierId, String alertType,
//                              String description, String alertLevel) {
//         this.supplierId = supplierId;
//         this.alertType = alertType;
//         this.description = description;
//         this.alertLevel = alertLevel;
//     }

//     // --- ADD THESE ALIAS METHODS TO PASS THE TESTS ---

//     /**
//      * This fixes the "cannot find symbol: method getRiskLevel()" error.
//      * It maps the test's request for 'RiskLevel' to your 'alertLevel' field.
//      */
//     public String getRiskLevel() { 
//         return this.alertLevel; 
//     }

//     public void setRiskLevel(String riskLevel) { 
//         this.alertLevel = riskLevel; 
//     }

//     // -------------------------------------------------

//     public Long getId() { return id; }
//     public void setId(Long id) { this.id = id; }

//     public Long getSupplierId() { return supplierId; }
//     public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

//     public String getAlertType() { return alertType; }
//     public void setAlertType(String alertType) { this.alertType = alertType; }

//     public String getDescription() { return description; }
//     public void setDescription(String description) { this.description = description; }

//     public Boolean getResolved() { return resolved; }
//     public void setResolved(Boolean resolved) { this.resolved = resolved; }

//     public String getAlertLevel() { return alertLevel; }
//     public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }
// }


package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "supplier_risk_alert")
public class SupplierRiskAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    private String alertType;
    private String description;
    private Boolean resolved = false;
    private String alertLevel;

    public SupplierRiskAlert() {}

    // ALIAS METHODS: Required to pass tests calling getRiskLevel/setRiskLevel
    public String getRiskLevel() { return this.alertLevel; }
    public void setRiskLevel(String riskLevel) { this.alertLevel = riskLevel; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public String getAlertType() { return alertType; }
    public void setAlertType(String alertType) { this.alertType = alertType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }
    public String getAlertLevel() { return alertLevel; }
    public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }
}