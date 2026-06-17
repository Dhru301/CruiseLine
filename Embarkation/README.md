# Embarkation Module — Standalone Project

Complete Spring Boot project for the Embarkation module ONLY.

## How to run

1. Extract this zip
2. Open the folder in VS Code
3. Open `src/main/resources/application.properties`
4. Set your MySQL password (replace `YOUR_MYSQL_PASSWORD_HERE`)
5. Open `CruiseLineApplication.java`
6. Click the green Run button above `main()`
7. Wait for "Tomcat started on port 8085"

Note: runs on port **8085** (not 8084) so you can run both projects at once.

Tables auto-create on first run:
- embarkation_records
- muster_stations
- muster_drill_attendance

## Test endpoints

- POST http://localhost:8085/api/embarkation/muster-stations
- POST http://localhost:8085/api/embarkation/check-in
- POST http://localhost:8085/api/embarkation/drills
- GET  http://localhost:8085/api/embarkation/voyages/{voyageId}/queue
