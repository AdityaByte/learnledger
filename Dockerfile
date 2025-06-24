# Stage 1: Build WAR
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Deploy WAR to Tomcat
FROM tomcat:10.1-jdk17
COPY --from=builder /app/target/learnledger.war /usr/local/tomcat/webapps/learnledger.war
EXPOSE 8080