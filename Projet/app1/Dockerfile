# Étape 1 : Construire l'application avec Maven
FROM maven:3.9.6-amazoncorretto-21 AS build-app1
WORKDIR /app1
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter l'application avec Amazon Corretto
FROM amazoncorretto:21.0.2-alpine3.19
WORKDIR /app
COPY --from=build-app1 /app1/target/*.jar /app/app1.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app1.jar"]
