# Etapa de build
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/shopxyz-0.0.1-SNAPSHOT.jar /app/shopxyz-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/shopxyz-0.0.1-SNAPSHOT.jar"]
