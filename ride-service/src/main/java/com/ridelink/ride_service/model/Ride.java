package com.ridelink.ride_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "rides")
public class Ride {
    @Id
    private String id;
    private String passengerId;
    private String driverId;
    private String pickupLocation;
    private String destination;
    private double distanceKm;
    private String vehicleType; // CAR, TUK, BIKE

    // Statuses: REQUESTED, ASSIGNED, ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED
    private String status;
    private LocalDateTime createdAt;
}
