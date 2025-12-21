package com.example.demo.entity;

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
[cite_start]public class DeliveryRecord { // [cite: 172]

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long poId; [cite_start]// [cite: 176]

    @NotNull
    private LocalDate actualDeliveryDate; [cite_start]// [cite: 181]

    @NotNull
    @Min(value = 0, message = "Delivered quantity must be >= 0")
    private Integer deliveredQuantity; [cite_start]// [cite: 183-187]

    @Column(length = 500)
    private String notes;
}