# Imagen base ligera con Java 21
FROM openjdk:21-jdk-slim

# Crear un directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado desde target
COPY target/jwtapi-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto (opcional para documentación)
EXPOSE 8080

# Ejecutar el JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
