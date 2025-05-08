# Étape 1 : Builder l'application avec Maven (Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter l'application dans une image légère
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copie le .jar généré depuis l'étape de build
COPY --from=build /app/target/*.jar sportcenter.jar

# Expose le port utilisé par l'application
EXPOSE 8081

# Démarre l'application
ENTRYPOINT ["java", "-jar", "sportcenter.jar"]