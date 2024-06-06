# Usar la imagen base de Maven para construir el JAR
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiar los archivos del proyecto y compilarlo
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Usar la imagen base de OpenJDK para ejecutar el JAR
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiar el JAR construido en la imagen final
COPY --from=build /app/target/citysos-0.0.1-SNAPSHOT.jar app.jar

# Definir ARGs que serán pasados durante el build
ARG RAILWAY_DB_HOST
ARG RAILWAY_DB_PORT
ARG RAILWAY_DB_NAME
ARG RAILWAY_DB_USERNAME
ARG RAILWAY_DB_PASSWORD

# Establecer las variables de entorno usando los ARGs
ENV RAILWAY_DB_HOST=$RAILWAY_DB_HOST
ENV RAILWAY_DB_PORT=$RAILWAY_DB_PORT
ENV RAILWAY_DB_NAME=$RAILWAY_DB_NAME
ENV RAILWAY_DB_USERNAME=$RAILWAY_DB_USERNAME
ENV RAILWAY_DB_PASSWORD=$RAILWAY_DB_PASSWORD

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
