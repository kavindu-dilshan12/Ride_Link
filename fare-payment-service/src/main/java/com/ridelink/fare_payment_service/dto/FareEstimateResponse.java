package com.ridelink.fare_payment_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FareEstimateResponse {
    private double distanceKm;
    private String vehicleType;
    private double estimatedFare;
    private String currency;
}
