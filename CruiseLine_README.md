# CruiseLine — Cruise Line Operations & Passenger Management System

A full-stack web application for running cruise-line operations: managing voyages and cabin
inventory, passenger bookings and embarkation, shore excursions, onboard accounts, analytics,
and notifications. Built with a Spring Boot REST API, a React + Bootstrap frontend, and MySQL.

This file is a project-level overview. Each sub-project (`cruiseline-backend`,
`cruiseline-frontend`) also contains its own README.

---

## Tech stack

**Backend**
- Java 17+ (built/run on JDK 21)
- Spring Boot 4.0.x (Spring 7, Jakarta EE 11, Jackson 3, Hibernate 7, Tomcat 11)
- Spring Data JPA + MySQL 8 (HikariCP)
- Spring Security with stateless JWT auth (jjwt, HS256), BCrypt password hashing
- springdoc-openapi (Swagger UI)

**Frontend**
- React 18 + Vite
- react-router-dom, react-bootstrap + bootstrap, bootstrap-icons
- axios (request interceptor attaches the JWT; one-time refresh-on-401 retry)

**Database**
- MySQL 8 (schema auto-created/updated by Hibernate `ddl-auto=update`)

---

## Prerequisites

- JDK 17 or newer (21 recommended)
- MySQL 8 running locally
- Node.js 18+ and npm

---

## Running the backend

1. Ensure MySQL is running; note your username/password.
2. Configure `cruiseline-backend/src/main/resources/application.properties` (or env vars):
   - `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` (defaults assume `root`/`root` at `localhost:3306`)
   - `JWT_SECRET` — **must be set to a strong random value** (see Security notes)
   - `ADMIN_PASSWORD` — initial admin password (default `Admin@12345`)
   - `CORS_ORIGINS` — allows `http://localhost:5173` and `:3000` by default
   - `server.port` — defaults to `8082`
3. Run it (IDE: *Run As → Spring Boot App*, or `mvn spring-boot:run`).
4. A default admin account is seeded on first run: **admin@cruiseline.com / Admin@12345**
5. Swagger UI: `http://localhost:8082/swagger-ui.html`

The database `cruiseline_db` and its tables are created automatically on first start.

## Running the frontend

```
cd cruiseline-frontend
npm install
npm run dev
```

- Dev server: `http://localhost:5173`
- API base URL comes from `.env` (`VITE_API_BASE_URL=http://localhost:8082`); change it there if
  the backend port differs, then restart `npm run dev`.
- The backend must be running for login and all data to work.

Log in with the seeded admin, or register a new passenger from the login screen.

---

## Roles

| Role | Responsibilities |
|------|------------------|
| PASSENGER | Books voyages/cabins and shore excursions, views own account and notifications |
| EMBARKATION_OFFICER | Passenger check-in, muster stations, drill attendance |
| ONBOARD_AGENT | Posts onboard charges to passenger accounts |
| EXCURSION_COORDINATOR | Manages excursions and port-day manifests |
| PURSER | Opens/settles onboard accounts, posts and reverses charges |
| ADMIN | Full access: voyages, cabin inventory, user administration, analytics |

Access control is enforced in two layers: the React UI filters navigation and guards routes by
role, and the backend independently enforces authorization with `@PreAuthorize` on endpoints.

---

## Security notes

- **Stateless JWT authentication** (access + refresh tokens), not server-side sessions. A JWT filter
  validates each request; Spring Security uses a stateless session policy.
- **Set `JWT_SECRET` to a strong, random value**; never commit a real secret. The value in
  `application.properties` is a placeholder — prefer injecting via an environment variable. Changing
  the secret invalidates existing tokens (users must log in again).
- **Public registration is restricted to PASSENGER.** `POST /api/auth/register` always creates a
  PASSENGER regardless of any role in the request. Staff/admin accounts are created only by an admin
  via the protected `POST /api/users` endpoint (privilege separation).
- Passwords are BCrypt-hashed. CORS is restricted to configured origins.

---

## Business rules enforced

- **Bookings accepted only while a voyage is OPEN** (PLANNING/SAILING/COMPLETED/CANCELLED are rejected).
- A booking becomes **CONFIRMED** only when paid in full; partial payment stays TENTATIVE.
- **Payments cannot exceed the outstanding balance**; payments are blocked on cancelled bookings.
- A cabin is bookable only when **AVAILABLE**; booking locks it and decrements availability.
- Onboard charges cannot exceed the account **credit limit**; settlement zeroes the balance and
  closes the account to further charges.

---

## Known limitations / future enhancements

Deliberate simplifications for this phase, consistent with the specification:

- **Round-trip voyages only** — single `homePort` + `returnDate`, no separate disembarkation port.
- **One credit limit per voyage** — no partial mid-voyage pay-down; settlement is a final close-out
  (charge *reversal* exists for corrections).
- **`vesselId` is free text**, not a foreign key to a Vessel entity; no fleet-management module.
- **Passport numbers** stored as plain text; encryption-at-rest would be a production step.
- Some free-text fields (e.g. phone) are not format-validated.
- Schema uses `ddl-auto=update`; production should use a migration tool (e.g. Flyway) with `validate`.

---

## Modules

1. Identity & Access Management (auth, users, roles)
2. Voyage & Cabin Inventory
3. Passenger Booking & Profile Management
4. Embarkation & Muster Management
5. Shore Excursion Management (incl. port-day manifests)
6. Onboard Accounts & Folio Settlement
7. Voyage Analytics
8. Notifications

Every backend endpoint is reachable from the frontend UI.
