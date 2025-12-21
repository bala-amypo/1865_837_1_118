package com.example.demo.service.impl;
import com.example.demo.model.DeliveryRecord;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.DeliveryRecordRepository;
import com.example.demo.repository.PurchaseOrderRecordRepository;
import com.example.demo.service.DeliveryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryRecordServiceImpl implements DeliveryRecordService {
    private final DeliveryRecordRepository deliveryRepository;
    private final PurchaseOrderRecordRepository poRepository;

    @Override
    public DeliveryRecord recordDelivery(DeliveryRecord delivery) {
        if (!poRepository.existsById(delivery.getPoId())) {
            throw new BadRequestException("Invalid PO id");
        }
        if (delivery.getDeliveredQuantity() < 0) {
            throw new BadRequestException("Quantity cannot be negative");
        }
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<DeliveryRecord> getDeliveriesByPO(Long poId) {
        return deliveryRepository.findByPoId(poId);
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public List<DeliveryRecord> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
}