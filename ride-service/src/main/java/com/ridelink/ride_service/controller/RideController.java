package com.ridelink.ride_service.controller;

import com.ridelink.ride_service.model.Ride;
import com.ridelink.ride_service.service.RideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rides")
@Tag(name = "Ride Management", description = "Endpoints for managing Ride Requests and Lifecycle")
public class RideController {

    @Autowired
    private RideService rideService;

    @PostMapping("/request")
    @Operation(summary = "Request a new Ride")
    public ResponseEntity<Ride> requestRide(@RequestBody Ride ride) {
        return new ResponseEntity<>(rideService.requestRide(ride), HttpStatus.CREATED);
    }

    @PutMapping("/{rideId}/assign-driver")
    @Operation(summary = "Assign Driver to a Ride")
    public ResponseEntity<Ride> assignDriver(@PathVariable String rideId, @RequestParam String driverId) {
        return ResponseEntity.ok(rideService.assignDriver(rideId, driverId));
    }

    @PutMapping("/{rideId}/status")
    @Operation(summary = "Update Ride status (ACCEPTED, IN_PROGRESS, COMPLETED, CANCELLED)")
    public ResponseEntity<Ride> updateRideStatus(@PathVariable String rideId, @RequestParam String status) {
        return ResponseEntity.ok(rideService.updateRideStatus(rideId, status));
    }

    @GetMapping
    @Operation(summary = "Get all rides")
    public ResponseEntity<List<Ride>> getAllRides() {
        return ResponseEntity.ok(rideService.getAllRides());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get ride by ID")
    public ResponseEntity<Ride> getRideById(@PathVariable String id) {
        return ResponseEntity.ok(rideService.getRideById(id));
    }
}
