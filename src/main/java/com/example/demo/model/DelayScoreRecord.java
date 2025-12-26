package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "delay_score_records")
public class DelayScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    
    @Column(unique = true)
    private Long poId;

    private Integer delayDays;
    private String delaySeverity; // ON_TIME, MINOR, MODERATE, SEVERE
    private Double score;
    private LocalDateTime computedAt;
}