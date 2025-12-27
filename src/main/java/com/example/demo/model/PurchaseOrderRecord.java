package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class PurchaseOrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ TESTS EXPECT supplierId, NOT Supplier object
    private Long supplierId;

    private String poNumber;
    private Integer quantity;
    private LocalDate issuedDate;
    private LocalDate promisedDeliveryDate;

    // ✅ REQUIRED BY SERVICES & TESTS
    public Long getId() {
        return id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public LocalDate getPromisedDeliveryDate() {
        return promisedDeliveryDate;
    }

    public void setPromisedDeliveryDate(LocalDate promisedDeliveryDate) {
        this.promisedDeliveryDate = promisedDeliveryDate;
    }
}
