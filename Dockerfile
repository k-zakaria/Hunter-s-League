# Étape 1 : Construction
FROM eclipse-temurin:17-jdk-alpine as build

WORKDIR /workspace/app

# Copie des fichiers nécessaires pour Maven Wrapper
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Construction du projet avec Maven, en évitant les tests pour accélérer le processus
RUN ./mvnw install -DskipTests

# Extraction des dépendances de l'archive JAR générée
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Étape 2 : Exécution
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Définir un argument pour le port et une variable d'environnement
ARG PORT=8080
ENV PORT=${PORT}

# Copier les fichiers nécessaires depuis l'étape de build
COPY --from=build /workspace/app/target/dependency/ /app/lib/
COPY --from=build /workspace/app/target/*.jar /app/app.jar

# Exposer le port pour l'application
EXPOSE ${PORT}

# Commande d'exécution par défaut
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
