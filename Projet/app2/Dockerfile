# Étape 1 : Construire l'application avec Maven
FROM maven:3.9.6-amazoncorretto-21 AS build-app2
WORKDIR /app2
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter l'application avec Amazon Corretto
FROM amazoncorretto:21.0.2-alpine3.19
WORKDIR /app
COPY --from=build-app2 /app2/target/*.jar /app/app2.jar
EXPOSE 8082
CMD sleep 15
ENTRYPOINT ["java", "-jar", "/app/app2.jar"]
