package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_record")
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long poId;
    private Integer deliveredQuantity;
    private LocalDate actualDeliveryDate;

    // ✅ REQUIRED no-args constructor
    public DeliveryRecord() {
    }

    // ✅ SAFE constructor for tests
    public DeliveryRecord(Long poId, Integer deliveredQuantity, LocalDate actualDeliveryDate) {
        this.poId = poId;
        this.deliveredQuantity = deliveredQuantity;
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPoId() { return poId; }
    public void setPoId(Long poId) { this.poId = poId; }

    public Integer getDeliveredQuantity() { return deliveredQuantity; }
    public void setDeliveredQuantity(Integer deliveredQuantity) { this.deliveredQuantity = deliveredQuantity; }

    public LocalDate getActualDeliveryDate() { return actualDeliveryDate; }
    public void setActualDeliveryDate(LocalDate actualDeliveryDate) { this.actualDeliveryDate = actualDeliveryDate; }
}
