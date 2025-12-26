package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_order_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}