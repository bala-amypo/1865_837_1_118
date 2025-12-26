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

    private Long poId;
    private LocalDate actualDeliveryDate;
    private Integer deliveredQuantity;
    private String notes;
}