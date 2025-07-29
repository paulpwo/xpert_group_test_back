# 🔨 Etapa de build
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Cache de dependencias
COPY pom.xml ./
COPY .mvn/ .mvn/
COPY mvnw ./
RUN ./mvnw dependency:go-offline -B

# Copiar código
COPY src ./src

# Build sin tests
RUN ./mvnw clean package -DskipTests

# 🏗 Producción con openjdk slim
FROM openjdk:17-slim

# Crear usuario seguro
RUN groupadd -r appgroup && useradd -r -g appgroup appuser

WORKDIR /app

# Copiar JAR desde el build
COPY --from=build /app/target/*.jar app.jar

RUN chown appuser:appgroup app.jar

USER appuser

EXPOSE 8080

# Healthcheck
HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 \
  CMD wget -qO- http://localhost:8080/actuator/health || exit 1

# Iniciar app
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "app.jar"]