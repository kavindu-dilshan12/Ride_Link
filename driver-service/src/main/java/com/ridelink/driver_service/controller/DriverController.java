package com.ridelink.driver_service.controller;

import com.ridelink.driver_service.model.Driver;
import com.ridelink.driver_service.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drivers")
@Tag(name = "Driver Management", description = "Endpoints for managing Drivers and Vehicles")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    @Operation(summary = "Register a new Driver with Vehicle details")
    public ResponseEntity<Driver> registerDriver(@RequestBody Driver driver) {
        return new ResponseEntity<>(driverService.registerDriver(driver), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all drivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Driver details by ID")
    public ResponseEntity<Driver> getDriverById(@PathVariable String id) {
        return ResponseEntity.ok(driverService.getDriverById(id));
    }

    @PutMapping("/{id}/availability")
    @Operation(summary = "Update Driver availability status (true/false)")
    public ResponseEntity<Driver> updateAvailability(@PathVariable String id, @RequestParam boolean isAvailable) {
        return ResponseEntity.ok(driverService.updateAvailability(id, isAvailable));
    }

    @PutMapping("/{id}/location")
    @Operation(summary = "Update Driver's current GPS location")
    public ResponseEntity<Driver> updateLocation(@PathVariable String id, @RequestParam double latitude, @RequestParam double longitude) {
        return ResponseEntity.ok(driverService.updateLocation(id, latitude, longitude));
    }

    @GetMapping("/available")
    @Operation(summary = "Find available drivers by vehicle type")
    public ResponseEntity<List<Driver>> getAvailableDrivers(@RequestParam String vehicleType) {
        return ResponseEntity.ok(driverService.getAvailableDriversByVehicleType(vehicleType));
    }
}
