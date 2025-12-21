package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "delay_score_records")
@Data
@NoArgsConstructor
[cite_start]public class DelayScoreRecord { // [cite: 200]

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supplierId; [cite_start]// [cite: 203]

    @Column(unique = true)
    private Long poId; [cite_start]// [cite: 205]

    private Integer delayDays; [cite_start]// [cite: 207]
    
    private String delaySeverity; [cite_start]// [cite: 211]
    
    private Double score; [cite_start]// [cite: 219]

    @Column(nullable = false, updatable = false)
    private LocalDateTime computedAt;

    @PrePersist
    protected void onCreate() {
        this.computedAt = LocalDateTime.now();
    }
}