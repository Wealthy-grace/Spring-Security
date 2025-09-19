# === Build stage ===
# Use a specific Gradle version with JDK 17
FROM gradle:8.7-jdk17 AS build
WORKDIR /app

# Copy build files first for better caching
COPY build.gradle settings.gradle /app/
COPY gradlew /app/
COPY gradle /app/gradle

# Copy source code
COPY src /app/src

# Make gradlew executable and build the application
RUN chmod +x gradlew
RUN ./gradlew clean bootJar --no-daemon

# === Runtime stage ===
# Use a lightweight JRE image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
