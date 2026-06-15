# CruiseLine — Backend (Spring Boot)

Cruise Line Operations & Passenger Management System. REST API backend built with
Spring Boot 3, Spring Security (JWT), Spring Data JPA, and MySQL.

## Tech stack

- Java 17, Maven
- Spring Boot 4.0.6 (Web, Data JPA, Security, Validation, Actuator) — built on Spring Framework 7, Jakarta EE 11, Jackson 3
- MySQL 8 (HikariCP connection pool)
- JWT auth (jjwt) with RBAC and method-level security
- springdoc-openapi 3.0.3 (Swagger UI) — the 3.x line is required for Spring Boot 4
- (Lombok removed — plain Java getters/setters/constructors, nothing to install)

> Built and verified against Spring Boot 4.0.6. Spring Boot 4 requires **Java 17 or newer** (compatible up to Java 26).

## Project layout

```
com.cruiseline
├── config/            Security, JWT, CORS, OpenAPI, data bootstrap
├── exception/         Global exception handler + custom exceptions
├── common/            Base entity, enums, shared DTOs, audit service
└── modules/
    ├── auth/          2.1 Identity & Access (users, JWT, audit log)
    ├── voyage/        2.2 Voyage & cabin inventory
    ├── booking/       2.3 Passenger booking & profiles
    ├── embarkation/   2.4 Embarkation & muster
    ├── excursion/     2.5 Shore excursions
    ├── account/       2.6 Onboard accounts & charges
    ├── analytics/     2.7 Voyage analytics
    └── notification/  2.8 Notifications & alerts
```

Each module follows `entity → repository → service → controller → dto`.

## Running locally

1. Install Java 17+ and MySQL 8.
2. Create the database (or let the app create it — the URL has `createDatabaseIfNotExist=true`):
   ```sql
   CREATE DATABASE cruiseline_db;
   ```
3. Set your DB credentials (see "Where to make changes" below).
4. Run:
   ```bash
   mvn spring-boot:run
   ```
5. The API is at `http://localhost:8080`. Swagger UI: `http://localhost:8080/swagger-ui.html`.

On first startup a default admin is seeded:
- email: `admin@cruiseline.com`
- password: `Admin@12345`

## Authentication flow

1. `POST /api/auth/login` with `{ "email": "...", "password": "..." }` → returns `accessToken` + `refreshToken`.
2. Send `Authorization: Bearer <accessToken>` on every protected request.
3. `POST /api/auth/refresh-token` with the refresh token to get a new access token.

## Roles

`PASSENGER`, `EMBARKATION_OFFICER`, `ONBOARD_AGENT`, `EXCURSION_COORDINATOR`, `PURSER`, `ADMIN`.
Endpoints are guarded with `@PreAuthorize`.

## Where to make changes

Everything sensitive is read from environment variables (with dev defaults in
`src/main/resources/application.properties`). For real use, change these:

1. Database — set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` (or edit the defaults in
   `application.properties`).
2. JWT secret — set `JWT_SECRET` to your own 256-bit+ value. The default is a
   placeholder and must not be used in production.
3. Default admin — change `ADMIN_EMAIL` / `ADMIN_PASSWORD`, or change the password
   after first login.
4. CORS — set `CORS_ORIGINS` to your frontend origin (default allows Vite/CRA dev ports).
5. Schema management — `JPA_DDL_AUTO` defaults to `update` for convenience. For
   production set it to `validate` and manage schema with Flyway or Liquibase.
6. Passport encryption — `PassengerDetail.passportNumber` is stored in plain text and
   marked with a TODO. Add a JPA `AttributeConverter` to encrypt it at rest before
   handling real PII.

## Notes / Phase-1 scope

Payments are recorded manually (no gateway). Notifications are in-app only. Visa /
immigration checks are manual. These match the project's Phase-1 constraints.
