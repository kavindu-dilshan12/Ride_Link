package com.ridelink.ride_service.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {

    // Connection string comes from the MONGODB_URI environment variable (never commit credentials).
    @Value("${MONGODB_URI:mongodb://localhost:27017}")
    private String mongoUri;

    // 1. Mongo client creation
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    // 2. Database factory setup (unique DB name for Ride Service)
    @Bean
    public SimpleMongoClientDatabaseFactory mongoDbFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), "ridelink_ride_db");
    }

    // 3. MongoTemplate creation
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}
