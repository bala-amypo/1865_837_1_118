package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "purchase_order_records")
public class PurchaseOrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String poNumber;
    
    private Long supplierId;
    private String itemDescription;
    private Integer quantity;
    private LocalDate promisedDeliveryDate;
    private LocalDate issuedDate;

    public PurchaseOrderRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPoNumber() { return poNumber; }
    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }
    public Long getSupplierId() { return supplierId; }
    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }
    public String getItemDescription() { return itemDescription; }
    public void setItemDescription(String itemDescription) { this.itemDescription = itemDescription; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public LocalDate getPromisedDeliveryDate() { return promisedDeliveryDate; }
    public void setPromisedDeliveryDate(LocalDate promisedDeliveryDate) { this.promisedDeliveryDate = promisedDeliveryDate; }
    public LocalDate getIssuedDate() { return issuedDate; }
    public void setIssuedDate(LocalDate issuedDate) { this.issuedDate = issuedDate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderRecord that = (PurchaseOrderRecord) o;
        return Objects.equals(id, that.id) && Objects.equals(poNumber, that.poNumber) && Objects.equals(supplierId, that.supplierId) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, poNumber, supplierId, quantity);
    }

    @Override
    public String toString() {
        return "PurchaseOrderRecord{id=" + id + ", poNumber='" + poNumber + '\'' + ", supplierId=" + supplierId + ", quantity=" + quantity + '}';
    }
}