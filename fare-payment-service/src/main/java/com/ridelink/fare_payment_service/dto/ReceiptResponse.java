package com.ridelink.fare_payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReceiptResponse {
    private String paymentId;
    private String transactionId;
    private String rideId;
    private String passengerId;
    private String driverId;
    private double distanceKm;
    private double totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime timestamp;
}
