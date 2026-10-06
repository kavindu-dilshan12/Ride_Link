package com.ridelink.fare_payment_service.dto;

import lombok.Data;

@Data
public class FareEstimateRequest {
    private double distanceKm;
    private String vehicleType; // CAR, TUK, BIKE
}
