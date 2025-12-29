package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_order_record")
public class PurchaseOrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    private String poNumber;
    private Integer quantity;
    private LocalDate promisedDeliveryDate;
    private LocalDate issuedDate;

    // ✅ REQUIRED by JPA
    public PurchaseOrderRecord() {
    }

    // ✅ SAFE constructor for tests
    public PurchaseOrderRecord(Long supplierId, String poNumber, Integer quantity,
                               LocalDate promisedDeliveryDate, LocalDate issuedDate) {
        this.supplierId = supplierId;
        this.poNumber = poNumber;
        this.quantity = quantity;
        this.promisedDeliveryDate = promisedDeliveryDate;
        this.issuedDate = issuedDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public String getPoNumber() { return poNumber; }
    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public LocalDate getPromisedDeliveryDate() { return promisedDeliveryDate; }
    public void setPromisedDeliveryDate(LocalDate promisedDeliveryDate) {
        this.promisedDeliveryDate = promisedDeliveryDate;
    }

    public LocalDate getIssuedDate() { 
    return issuedDate; 
    }
    public void setIssuedDate(LocalDate issuedDate) { 
    this.issuedDate = issuedDate; 
    }
}
