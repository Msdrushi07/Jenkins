# Use official OpenJDK runtime
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR file
COPY target/Jenkins-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 9090

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
