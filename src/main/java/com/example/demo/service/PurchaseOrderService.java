package com.example.demo.service;

import com.example.demo.model.PurchaseOrderRecord;
import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {

    PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord po);

    List<PurchaseOrderRecord> getAllPurchaseOrders();

    Optional<PurchaseOrderRecord> getPOById(Long id);

    List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId);
}
