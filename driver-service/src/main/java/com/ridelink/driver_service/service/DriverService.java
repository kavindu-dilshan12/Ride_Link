package com.ridelink.driver_service.service;

import com.ridelink.driver_service.model.Driver;
import com.ridelink.driver_service.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    // 1. Register Driver Profile
    public Driver registerDriver(Driver driver) {
        driver.setAvailable(true); // Default status: Available
        return driverRepository.save(driver);
    }

    // 2. Get All Drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // 3. Get Driver By ID
    public Driver getDriverById(String id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found with ID: " + id));
    }

    // 4. Update Driver Availability Status
    public Driver updateAvailability(String id, boolean isAvailable) {
        Driver driver = getDriverById(id);
        driver.setAvailable(isAvailable);
        return driverRepository.save(driver);
    }

    // 5. Update Location
    public Driver updateLocation(String id, double latitude, double longitude) {
        Driver driver = getDriverById(id);
        driver.setCurrentLatitude(latitude);
        driver.setCurrentLongitude(longitude);
        return driverRepository.save(driver);
    }

    // 6. Get Available Drivers by Vehicle Type
    public List<Driver> getAvailableDriversByVehicleType(String vehicleType) {
        return driverRepository.findByIsAvailableAndVehicleType(true, vehicleType);
    }
}
