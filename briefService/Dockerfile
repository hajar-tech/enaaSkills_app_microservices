# Étape 1 : Image de base avec Java 17
FROM eclipse-temurin:17-jdk

# Étape 2 : Dossier de travail
WORKDIR /app

# Étape 3 : Copier le JAR généré par Maven
COPY target/briefService-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : Port exposé (facultatif mais recommandé)
EXPOSE 8080

# Étape 5 : Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
