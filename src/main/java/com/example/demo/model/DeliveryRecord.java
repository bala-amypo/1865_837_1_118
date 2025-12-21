package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "po_id", nullable = false)
    private Long poId;

    @Column(name = "actual_delivery_date", nullable = false)
    private LocalDate actualDeliveryDate;

    @Column(name = "delivered_quantity", nullable = false)
    private Integer deliveredQuantity;

    @Column(name = "notes")
    private String notes;
}