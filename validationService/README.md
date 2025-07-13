# Service de Validation

## Vue d'ensemble
Le Service de Validation (Validation Service) fait partie du système de microservices pour la gestion des compétences. Ce service gère les processus de validation des compétences des apprenants.

## Communication avec les autres services

### 1. Communication REST API
Le service communique avec les autres services via des APIs REST :

- **Service Apprenant** (Port 8081) : pour vérifier l'existence de l'apprenant
- **Service Brief** (Port 8082) : pour vérifier l'existence du projet
- **Service Compétence** (Port 8083) : pour vérifier l'existence de la compétence

### 2. Comment fonctionne la communication

#### Lors de la création d'une nouvelle validation :
1. Vérifie l'existence de l'apprenant dans le Service Apprenant
2. Vérifie l'existence du projet dans le Service Brief
3. Vérifie l'existence de la compétence dans le Service Compétence
4. Vérifie qu'il n'y a pas de validation en double
5. Crée la validation si toutes les conditions sont remplies

#### Exemple de requête :
```json
POST /api/validations
{
  "apprenantId": 1,
  "competenceId": 2,
  "briefId": 3,
  "statut": "EN_ATTENTE"
}
```

### 3. Configuration de la communication

Les adresses des autres services peuvent être configurées dans `application.properties` :

```properties
# URLs pour les autres services
apprenant.service.url=http://localhost:8081/api/apprenants
brief.service.url=http://localhost:8082/api/briefs
competence.service.url=http://localhost:8083/api/competences
```

### 4. Gestion des erreurs

Si les autres services ne sont pas disponibles, une erreur appropriée sera retournée :
- "Apprenant avec ID X non trouvé"
- "Brief avec ID X non trouvé"
- "Compétence avec ID X non trouvée"

## Démarrage du service

### Pour le développement local (avec H2 Database) :
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### Pour la production (avec MySQL) :
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Avec Docker :
```bash
docker-compose up --build
```

## Endpoints API

- `GET /api/validations` - Récupérer toutes les validations
- `GET /api/validations/{id}` - Récupérer une validation spécifique
- `POST /api/validations` - Créer une nouvelle validation
- `PUT /api/validations/{id}` - Mettre à jour une validation
- `DELETE /api/validations/{id}` - Supprimer une validation
- `GET /api/validations/apprenant/{apprenantId}` - Validations d'un apprenant spécifique
- `GET /api/validations/brief/{briefId}` - Validations d'un projet spécifique
- `GET /api/validations/competence/{competenceId}` - Validations d'une compétence spécifique

## Swagger UI
La documentation de l'API est accessible via :
http://localhost:8080/swagger-ui.html 