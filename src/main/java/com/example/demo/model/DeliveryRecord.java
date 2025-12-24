package com.example.demo.model;

import java.time.LocalDate;

public class DeliveryRecord {
    private Long id;
    private Long poId;
    private Integer deliveredQuantity;
    private LocalDate actualDeliveryDate;

    public DeliveryRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPoId() { return poId; }
    public void setPoId(Long poId) { this.poId = poId; }

    public Integer getDeliveredQuantity() { return deliveredQuantity; }
    public void setDeliveredQuantity(Integer deliveredQuantity) { this.deliveredQuantity = deliveredQuantity; }

    public LocalDate getActualDeliveryDate() { return actualDeliveryDate; }
    public void setActualDeliveryDate(LocalDate actualDeliveryDate) { this.actualDeliveryDate = actualDeliveryDate; }
}
