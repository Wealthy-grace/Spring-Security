# --- Stage 1: Build the application using Gradle with JDK 21 ---
FROM gradle:8.5-jdk21 AS build

WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .

# Skip tests during build for faster CI/CD (optional)
RUN gradle clean build -x test --no-daemon

# --- Stage 2: Run the built JAR using a lightweight JDK 21 image ---
FROM openjdk:21-jdk-slim AS production

# Set a working directory
WORKDIR /app

# Copy the jar built from the Gradle stage
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

# Expose port 8080 (matches docker-compose)
EXPOSE 8080

# Optional: Environment variables for timezone, locale
ENV TZ=UTC \
    LANG=C.UTF-8

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
