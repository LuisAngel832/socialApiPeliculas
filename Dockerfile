# Etapa 1: Build con Maven
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen ligera para ejecutar
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto que usa Spring Boot
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
