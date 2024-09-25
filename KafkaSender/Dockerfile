# Maven
FROM maven:3.8.4-openjdk-17-slim AS builder
WORKDIR /app
COPY ./pom.xml ./
RUN mvn -e -B dependency:resolve
COPY ./src ./src
RUN mvn clean package -DskipTests

# RTSDK Java
FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY --from=builder /app/target/KafkaSender-0.0.1-SNAPSHOT.jar .
# Set the command to run the application
CMD ["java", "-jar", "KafkaSender-0.0.1-SNAPSHOT.jar"]