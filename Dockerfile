# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 AS build

# 1. Install Node.js (Required for Vaadin to build the frontend)
RUN apt-get update && apt-get install -y nodejs npm

COPY . .

# 2. Add "-Pproduction" to activate the Vaadin build profile
RUN mvn clean package -DskipTests -Pproduction

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]