# Image Java officielle (JDK 17)
FROM eclipse-temurin:17-jdk

# Répertoire de travail dans le container
WORKDIR /app

# Copier le fichier JAR généré
COPY target/apprenantService-0.0.1-SNAPSHOT.jar app.jar

# Commande de lancement
ENTRYPOINT ["java", "-jar", "app.jar"]
