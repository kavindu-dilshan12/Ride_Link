package com.ridelink.ride_service.service;

import com.ridelink.ride_service.model.Ride;
import com.ridelink.ride_service.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private RestTemplate restTemplate;

    // 1. Request a Ride
    public Ride requestRide(Ride ride) {
        ride.setStatus("REQUESTED");
        ride.setCreatedAt(LocalDateTime.now());
        return rideRepository.save(ride);
    }

    // 2. Assign Driver to Ride (calls the Driver Service to check the driver exists)
    public Ride assignDriver(String rideId, String driverId) {
        Ride ride = getRideById(rideId);

        // Synchronous REST call to the Driver Service on port 8082
        String driverServiceUrl = "http://localhost:8082/api/v1/drivers/" + driverId;
        try {
            Object driverResponse = restTemplate.getForObject(driverServiceUrl, Object.class);
            if (driverResponse != null) {
                ride.setDriverId(driverId);
                ride.setStatus("ASSIGNED");
                return rideRepository.save(ride);
            }
        } catch (Exception e) {
            throw new RuntimeException("Driver not found or Driver Service unavailable!");
        }

        throw new RuntimeException("Failed to assign driver.");
    }

    // 3. Update Ride Status (ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED)
    public Ride updateRideStatus(String rideId, String status) {
        Ride ride = getRideById(rideId);
        ride.setStatus(status.toUpperCase());
        return rideRepository.save(ride);
    }

    public Ride getRideById(String id) {
        return rideRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ride not found with ID: " + id));
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }
}
