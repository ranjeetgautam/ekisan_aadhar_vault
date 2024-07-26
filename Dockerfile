# Use an official OpenJDK runtime as a parent image
FROM openjdk:8-jdk-slim

ENV TZ=Asia/Kolkata

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app
COPY vault-0.0.1-SNAPSHOT.jar vault-0.0.1-SNAPSHOT.jar

# Expose the port that the application will run on
EXPOSE 9001

# Specify the command to run on container startup
CMD ["java", "-jar", "vault-0.0.1-SNAPSHOT.jar"]