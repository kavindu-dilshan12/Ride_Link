# RideLink Microservices Backend

RideLink is a backend-only ride-sharing system built for IT3130 Application Development. It consists of four independently runnable Spring Boot microservices (Java 17, Maven, MongoDB). All data and payments are simulated.

| Service | Folder | Port | Database |
|---|---|---|---|
| Account | `account-service` | 8081 | `ridelink_account_db` |
| Driver & Vehicle | `driver-service` | 8082 | `ridelink_driver_db` |
| Ride Management | `ride-service` | 8083 | `ridelink_ride_db` |
| Fare & Payment | `fare-payment-service` | 8084 | `ridelink_payment_db` |

Only the Ride service calls another service (Driver service, `GET /api/v1/drivers/{id}`, when a driver is assigned).

## Prerequisites
- JDK 17 and Maven 3.9+ (or the included `mvnw` wrapper)
- A MongoDB instance (local `mongodb://localhost:27017` or MongoDB Atlas)

## Configuration (environment variables)
Credentials are never stored in the repository.

| Variable | Used by | Purpose |
|---|---|---|
| `MONGODB_URI` | all services | MongoDB connection string. Defaults to `mongodb://localhost:27017`. |
| `JWT_SECRET` | account-service | JWT signing secret, at least 32 characters. Required. |

PowerShell example:
```powershell
$env:MONGODB_URI = "mongodb://localhost:27017"
$env:JWT_SECRET = "replace-with-a-long-random-secret-value-32chars"
```

Bash example:
```bash
export MONGODB_URI="mongodb://localhost:27017"
export JWT_SECRET="replace-with-a-long-random-secret-value-32chars"
```

## Running
Start each service in its own terminal:
```bash
cd account-service && mvn spring-boot:run
cd driver-service && mvn spring-boot:run
cd ride-service && mvn spring-boot:run
cd fare-payment-service && mvn spring-boot:run
```

Swagger UI: `http://localhost:<port>/swagger-ui.html` (ports above).

## Endpoints
- Account: `POST /api/v1/users/register`, `POST /api/v1/users/login`, `GET /api/v1/users`, `GET /api/v1/users/{id}`
- Driver: `POST /api/v1/drivers/register`, `GET /api/v1/drivers`, `GET /api/v1/drivers/{id}`, `GET /api/v1/drivers/available`, `PUT /api/v1/drivers/{id}/availability?isAvailable=`, `PUT /api/v1/drivers/{id}/location?latitude=&longitude=`
- Ride: `POST /api/v1/rides/request`, `PUT /api/v1/rides/{id}/assign-driver?driverId=`, `PUT /api/v1/rides/{id}/status?status=`, `GET /api/v1/rides`, `GET /api/v1/rides/{id}`
- Payment: `POST /api/v1/payments/estimate`, `POST /api/v1/payments/process`, `GET /api/v1/payments/receipt/{rideId}`

## Fare rule
Fare = base fare + distance (km) x rate per km, by vehicle type: BIKE 50 + 60/km, TUK 100 + 80/km, CAR 200 + 120/km, other 100 + 100/km (LKR).

## Tests and CI
Run `mvn test` inside a service folder. GitHub Actions (`.github/workflows/ci.yml`) runs `mvn -B clean test` for all four services on every push and pull request to `main`, using a MongoDB service container. The build fails if any test fails.

## Release
The assessed version is the Git tag `v1.0.0` on `main`.
