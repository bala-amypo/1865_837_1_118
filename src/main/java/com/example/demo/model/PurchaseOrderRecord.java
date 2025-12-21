package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_order_records")
@Data
@NoArgsConstructor
[cite_start]public class PurchaseOrderRecord { // [cite: 136]

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, length = 50)
    private String poNumber; [cite_start]// [cite: 139]

    @NotNull
    private Long supplierId; [cite_start]// [cite: 143]

    @NotBlank
    @Column(length = 500)
    private String itemDescription; [cite_start]// [cite: 148]

    @NotNull
    @Positive
    private Integer quantity; [cite_start]// [cite: 152]

    @NotNull
    private LocalDate promisedDeliveryDate; [cite_start]// [cite: 155]

    @NotNull
    private LocalDate issuedDate; [cite_start]// [cite: 158]
}