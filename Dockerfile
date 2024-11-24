# Use a base image with OpenJDK
FROM openjdk:17-jdk-slim

# Copy the built JAR file into the container
COPY target/Customer_Apis_Scalable_Assignment-1.0.0-SNAPSHOT.jar /app/Customer_Apis_Scalable_Assignment-1.0.0-SNAPSHOT.jar

# Expose the port the app will run on
EXPOSE 8082

# Set the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/Customer_Apis_Scalable_Assignment-1.0.0-SNAPSHOT.jar"]
