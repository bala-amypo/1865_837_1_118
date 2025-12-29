// package com.example.demo.service;

// import com.example.demo.model.SupplierRiskAlert;
// import java.util.List;

// public interface SupplierRiskAlertService {
//     SupplierRiskAlert createAlert(SupplierRiskAlert alert);
//     List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);
//     SupplierRiskAlert resolveAlert(Long alertId);
    
//     // Methods required by the failing tests
//     List<SupplierRiskAlert> getUnresolvedAlerts();
//     List<SupplierRiskAlert> getHighRiskAlerts();         // Required for testCriterialikeHighRiskSuppliers
//     List<SupplierRiskAlert> getAlertsByRisk(String risk); // Required for testCriteriaAlertMediumRisk
    
//     List<SupplierRiskAlert> getAllAlerts();
// }


package com.example.demo.service;

import com.example.demo.model.SupplierRiskAlert;
import java.util.List;

public interface SupplierRiskAlertService {
    SupplierRiskAlert createAlert(SupplierRiskAlert alert);
    List<SupplierRiskAlert> getAlertsBySupplier(Long supplierId);
    SupplierRiskAlert resolveAlert(Long alertId);
    List<SupplierRiskAlert> getUnresolvedAlerts();
    
    // These methods match the "Criteria" test names in your logs
    List<SupplierRiskAlert> getHighRiskAlerts(); 
    List<SupplierRiskAlert> getAlertsByRisk(String risk); 

    List<SupplierRiskAlert> getAllAlerts();
}