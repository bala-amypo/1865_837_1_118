package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delay_score_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId;
    
    @Column(unique = true) // One score per PO (Test 31)
    private Long poId;
    
    private Integer delayDays;
    private String delaySeverity; // ON_TIME, MINOR, MODERATE, SEVERE
    private Double score;
    
    private LocalDateTime computedAt;

    @PrePersist
    public void onCreate() {
        this.computedAt = LocalDateTime.now();
    }
}