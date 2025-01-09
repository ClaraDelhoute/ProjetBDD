# Étape 1 : Construire app1
FROM maven:3.9.6-amazoncorretto-21 AS build-app1
WORKDIR /app1
COPY /app1/pom.xml /app1/pom.xml
COPY /app1/src /app1/src
RUN mvn clean install

# Étape 2 : Construire app2
FROM maven:3.9.6-amazoncorretto-21 AS build-app2
WORKDIR /app2
COPY app2/pom.xml /app2/pom.xml
COPY app2/src /app2/src
COPY --from=build-app1 /root/.m2 /root/.m2
RUN mvn clean install

# Étape finale : Créer l'image pour exécuter app2
FROM amazoncorretto:21.0.2-alpine3.19
COPY --from=build-app2 /app2/target/*.jar /app2.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app2.jar"]
