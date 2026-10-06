package com.ridelink.fare_payment_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "payments")
public class Payment {
    @Id
    private String id;
    private String rideId;
    private String passengerId;
    private String driverId;
    private double amount;
    private String paymentMethod; // CASH, CARD, WALLET
    private String paymentStatus; // PENDING, SUCCESS, FAILED
    private String transactionId;
    private LocalDateTime timestamp;
}
