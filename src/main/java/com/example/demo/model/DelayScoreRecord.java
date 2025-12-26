package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
    
    @Column(unique = true)
    private Long poId;
    
    private Integer delayDays;
    private String delaySeverity; 
    private Double score;
    private LocalDateTime computedAt;

    @PrePersist
    public void onCreate() {
        this.computedAt = LocalDateTime.now();
    }
}