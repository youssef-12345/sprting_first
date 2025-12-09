# Stage 1: Build stage (if needed in future for multi-stage builds)
FROM eclipse-temurin:21-jdk-alpine AS builder

# Stage 2: Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy the built JAR from target directory
COPY target/supply-chain-management-1.0.0.jar app.jar

# Create non-root user for security (optional but recommended)
RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser

EXPOSE 8080

# Use exec form to ensure signals are properly forwarded
ENTRYPOINT ["java", "-jar", "app.jar"]
