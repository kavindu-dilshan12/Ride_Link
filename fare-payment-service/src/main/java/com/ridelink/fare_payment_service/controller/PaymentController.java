package com.ridelink.fare_payment_service.controller;

import com.ridelink.fare_payment_service.dto.*;
import com.ridelink.fare_payment_service.model.Payment;
import com.ridelink.fare_payment_service.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Fare & Payment Management", description = "Endpoints for Fare Estimation and Payment Processing")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/estimate")
    @Operation(summary = "Calculate Estimated Fare")
    public ResponseEntity<FareEstimateResponse> getFareEstimate(@RequestBody FareEstimateRequest request) {
        return ResponseEntity.ok(paymentService.calculateEstimate(request));
    }

    @PostMapping("/process")
    @Operation(summary = "Process Simulated Payment")
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.processPayment(request));
    }

    @GetMapping("/receipt/{rideId}")
    @Operation(summary = "Get Receipt by Ride ID")
    public ResponseEntity<ReceiptResponse> getReceipt(@PathVariable String rideId) {
        return ResponseEntity.ok(paymentService.getReceiptByRideId(rideId));
    }
}
