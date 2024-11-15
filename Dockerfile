# Use the official Maven image as the build stage
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory
WORKDIR /usr/src/app

# Copy pom.xml and source code into the container
COPY pom.xml .
COPY src ./src

# Copy application.example.yml to application.yml
RUN cp src/main/resources/application.example.yml src/main/resources/application.yml

# Execute Maven build
RUN mvn clean package -DskipTests

# Use OpenJDK image as the runtime stage
FROM openjdk:17-alpine

# Copy the built JAR file to the runtime image
COPY --from=build /usr/src/app/target/big-event-1.0-SNAPSHOT.jar /usr/app/big-event-1.0-SNAPSHOT.jar

# Expose the port required by the application
EXPOSE 8080

# Define the command to run when the container starts
ENTRYPOINT ["java", "-jar", "/usr/app/big-event-1.0-SNAPSHOT.jar"]