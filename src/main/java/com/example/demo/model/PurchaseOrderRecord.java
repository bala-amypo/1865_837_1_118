package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "purchase_order_records")
public class PurchaseOrderRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String poNumber;

    @NotNull
    private Long supplierId;

    private String itemDescription;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    private LocalDate promisedDeliveryDate;

    @NotNull
    private LocalDate issuedDate;
}