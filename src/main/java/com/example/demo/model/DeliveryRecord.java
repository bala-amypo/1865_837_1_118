package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ TESTS EXPECT poId, NOT PurchaseOrder object
    private Long poId;

    private Integer deliveredQuantity;
    private LocalDate actualDeliveryDate;

    // ✅ REQUIRED BY SERVICES & TESTS
    public Long getId() {
        return id;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public Integer getDeliveredQuantity() {
        return deliveredQuantity;
    }

    public void setDeliveredQuantity(Integer deliveredQuantity) {
        this.deliveredQuantity = deliveredQuantity;
    }

    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }
}
