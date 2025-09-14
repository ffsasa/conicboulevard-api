# ===== Stage 1: Build JAR (Maven + JDK 17) =====
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn -q -DskipTests clean package

# ===== Stage 2: Run app (JRE 17) =====
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/form-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
