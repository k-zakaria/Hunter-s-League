# Use a specific version tag for the base image to ensure consistency
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Optionally, if you have a specific JAR name, replace 'your-app.jar' with it
ARG JAR_FILE=target/maska_hunters_league.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app.jar

# Create a non-root user to run the application
RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup

# Change ownership of the application directory
RUN chown -R appuser:appgroup /app

# Switch to the non-root user
USER appuser

# Define the entrypoint
ENTRYPOINT ["java", "-jar", "app.jar"]

# Optional: Add a health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
