package com.example.demo.service;

import com.example.demo.model.PurchaseOrderRecord;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderService {

    PurchaseOrderRecord createPurchaseOrder(PurchaseOrderRecord purchaseOrderRecord);

    List<PurchaseOrderRecord> getPOsBySupplier(Long supplierId);

    Optional<PurchaseOrderRecord> getPOById(Long poId);

    List<PurchaseOrderRecord> getAllPurchaseOrders();
}
