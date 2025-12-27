package com.example.demo.repository;

import com.example.demo.model.*;
import java.util.*;

public interface DelayScoreRecordRepository {
    Optional<DelayScoreRecord> findByPoId(Long poId);
    List<DelayScoreRecord> findBySupplierId(Long supplierId);
    List<DelayScoreRecord> findAll();
    DelayScoreRecord save(DelayScoreRecord record);
}
