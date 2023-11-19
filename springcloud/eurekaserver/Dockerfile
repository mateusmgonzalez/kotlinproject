# Use the official OpenJDK image as a base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY build/libs/eurekaserver-0.0.1-SNAPSHOT.jar /app/eurekaserver-0.0.1-SNAPSHOT.jar

# Expose the port that the application will run on
EXPOSE 8761

# Specify the command to run on container start
CMD ["java", "-jar", "eurekaserver-0.0.1-SNAPSHOT.jar"]
