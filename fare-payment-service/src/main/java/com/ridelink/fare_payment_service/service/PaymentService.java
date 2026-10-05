package com.ridelink.fare_payment_service.service;

import com.ridelink.fare_payment_service.dto.*;
import com.ridelink.fare_payment_service.model.Payment;
import com.ridelink.fare_payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // 1. Calculate Estimated Fare
    public FareEstimateResponse calculateEstimate(FareEstimateRequest request) {
        double ratePerKm = getRatePerKm(request.getVehicleType());
        double baseFare = getBaseFare(request.getVehicleType());
        double totalFare = baseFare + (request.getDistanceKm() * ratePerKm);

        return new FareEstimateResponse(request.getDistanceKm(), request.getVehicleType(), totalFare, "LKR");
    }

    // 2. Process Simulated Payment
    public Payment processPayment(PaymentRequest request) {
        double ratePerKm = getRatePerKm(request.getVehicleType());
        double baseFare = getBaseFare(request.getVehicleType());
        double totalFare = baseFare + (request.getDistanceKm() * ratePerKm);

        Payment payment = new Payment();
        payment.setRideId(request.getRideId());
        payment.setPassengerId(request.getPassengerId());
        payment.setDriverId(request.getDriverId());
        payment.setAmount(totalFare);
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus("SUCCESS"); // Simulated success
        payment.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        payment.setTimestamp(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    // 3. Get Receipt by Ride ID
    public ReceiptResponse getReceiptByRideId(String rideId) {
        Payment payment = paymentRepository.findByRideId(rideId)
                .orElseThrow(() -> new RuntimeException("Payment receipt not found for Ride ID: " + rideId));

        return new ReceiptResponse(
                payment.getId(),
                payment.getTransactionId(),
                payment.getRideId(),
                payment.getPassengerId(),
                payment.getDriverId(),
                0.0, // Distance
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getTimestamp()
        );
    }

    private double getRatePerKm(String vehicleType) {
        if (vehicleType == null) return 100.0;
        return switch (vehicleType.toUpperCase()) {
            case "BIKE" -> 60.0;
            case "TUK" -> 80.0;
            case "CAR" -> 120.0;
            default -> 100.0;
        };
    }

    private double getBaseFare(String vehicleType) {
        if (vehicleType == null) return 100.0;
        return switch (vehicleType.toUpperCase()) {
            case "BIKE" -> 50.0;
            case "TUK" -> 100.0;
            case "CAR" -> 200.0;
            default -> 100.0;
        };
    }
}
