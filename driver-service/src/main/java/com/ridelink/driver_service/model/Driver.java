package com.ridelink.driver_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "drivers")
public class Driver {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String licenseNumber;

    // Vehicle Info
    private String vehicleNumber;
    private String vehicleModel;
    private String vehicleType; // CAR, TUK, BIKE

    // Status & Location
    private boolean isAvailable; // true = Available, false = Busy/Offline
    private double currentLatitude;
    private double currentLongitude;
}
