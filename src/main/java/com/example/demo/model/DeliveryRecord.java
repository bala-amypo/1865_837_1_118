package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "delivery_records")
@Data
@NoArgsConstructor
public class DeliveryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long poId;

    @NotNull
    private LocalDate actualDeliveryDate;

    @NotNull
    @Min(value = 0, message = "Delivered quantity must be >= 0")
    private Integer deliveredQuantity;

    @Column(length = 500)
    private String notes;
}