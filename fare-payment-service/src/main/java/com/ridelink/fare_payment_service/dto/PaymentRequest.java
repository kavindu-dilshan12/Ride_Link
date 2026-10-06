package com.ridelink.fare_payment_service.dto;

import lombok.Data;

@Data
public class PaymentRequest {
    private String rideId;
    private String passengerId;
    private String driverId;
    private double distanceKm;
    private String vehicleType;
    private String paymentMethod; // CASH, CARD, WALLET
}
