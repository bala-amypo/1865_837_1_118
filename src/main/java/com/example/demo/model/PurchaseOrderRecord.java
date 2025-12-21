package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_order_records", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"po_number"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "po_number", nullable = false, unique = true)
    private String poNumber;

    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @Column(name = "item_description", nullable = false)
    private String itemDescription;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "promised_delivery_date", nullable = false)
    private LocalDate promisedDeliveryDate;

    @Column(name = "issued_date", nullable = false)
    private LocalDate issuedDate;
}
