package com.ridelink.fare_payment_service.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    // Connection string comes from the MONGODB_URI environment variable (never commit credentials).
    @Value("${MONGODB_URI:mongodb://localhost:27017}")
    private String mongoUri;

    @Override
    protected String getDatabaseName() {
        return "ridelink_payment_db";
    }

    @Override
    public MongoClient mongoClient() {
        String connectionString = mongoUri;
        ConnectionString connString = new ConnectionString(connectionString);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .applyToSocketSettings(builder -> builder
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS))
                .applyToClusterSettings(builder -> builder
                        .serverSelectionTimeout(15, TimeUnit.SECONDS))
                .build();

        return MongoClients.create(settings);
    }
}
