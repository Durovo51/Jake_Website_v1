# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# 1. Install correct Node.js version (Vaadin requires Node 18+)
RUN curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs

# 2. Copy ONLY the build configuration first (Optimization)
# This allows Docker to cache dependencies so they don't re-download
# unless you actually change your pom.xml
COPY pom.xml .
RUN mvn dependency:go-offline

# 3. Copy the rest of the source code and build
COPY src ./src
COPY frontend ./frontend
# (Optional: Copy other folders if you have custom things like 'package.json')

# 4. Build the app
RUN mvn clean package -DskipTests -Pproduction

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# 5. Copy the jar dynamically (The wildcard *.jar finds the file even if you change versions)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]