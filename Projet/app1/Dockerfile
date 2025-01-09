# Étape 1 : Construire app1
FROM maven:3.9.6-amazoncorretto-21 AS build-app1
WORKDIR /app1
COPY /app1/pom.xml /app1/pom.xml
COPY /app1/src /app1/src
RUN mvn clean install

# Étape finale : Créer l'image pour exécuter app1
FROM amazoncorretto:21.0.2-alpine3.19
COPY --from=build-app1 /app1/target/*.jar /app1.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app1.jar"]