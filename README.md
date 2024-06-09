# Test Project created for GCAsh/Mynt

This project manages operation related to parcel delivery cost

## Setup Guide
- Install sdkman (optional) - https://sdkman.io/
- Install Maven v3.9 (or onwards)
- Install Java v21 (Eclipse Temurin https://adoptium.net/temurin/releases/) - use LTS version

## Start the app by running the command below
`mvn spring-boot:run`

## Endpoints
- **POST /v1/parcels/compute-cost**: computes parcel delivery cost based on default rules defined.