# STAGE 1: Build the application
# We use a Maven image that includes OpenJDK 17 (standard for modern Vaadin)
FROM maven:3.9-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download dependencies first (for caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of your source code
COPY src ./src

# Build the application in "production" mode
# Vaadin requires this to compile the frontend (CSS/JS) optimized for the web
RUN mvn clean package -Pproduction -DskipTests

# STAGE 2: Run the application
# We use a smaller, lighter Java image for the final running version
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the "build" stage
# Note: This assumes your pom.xml generates a jar named 'app.jar' or similar.
# The asterisk (*) grabs whatever jar was built.
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on (Spring Boot default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]