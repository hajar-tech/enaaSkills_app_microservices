
**📚 ENAA – Plateforme de Suivi des Compétences**

Plateforme Web de gestion et de suivi d’acquisition des compétences pédagogiques des apprenants de l'ENAA, basée sur une architecture microservices. Cette application permet aux formateurs de créer des briefs, de les associer à des compétences, de valider les rendus des apprenants, et d’assurer un suivi détaillé des compétences acquises.

🚀 **Fonctionnalités principales**

🎯 **Brief-Service**

Création d’un brief pédagogique (formateur)

Association de compétences à un brief

👨‍🎓 **Apprenant-Service**

Inscription d’un apprenant

Dépôt de rendus pour un brief

Consultation des rendus et des compétences validées

✅ **Validation-Service**

Validation d’une compétence d’un apprenant sur un brief (formateur)

Visualisation des validations de compétences (admin/formateur)

État global des compétences validées pour chaque apprenant

**🛠️ Technologies**

Backend : Spring Boot, Spring Data JPA

Base de données : MySQL / PostgreSQL (selon le service)

Communication inter-services : RestTemplate ou Feign Client

Tests : JUnit

Conteneurisation : Docker & Docker Compose




**CONCEPTION UML**

**Diagramme de classe**
![diagramme de calsse](UML/Capture%20d'écran%202025-07-07%20151244.png)

**Diagramme de cas d'utilisation**
![diagramme use case](UML/Capture%20d'écran%202025-07-08%20094810.png)


**test la validation sur swagger**

http://localhost:8080/swagger-ui/index.html#/


**test la validation sur postman**


https://.postman.co/workspace/My-Workspace~2be7e78b-294c-4e8d-8fab-bf07529f7a9c/collection/44577571-8e6f8ff7-b29e-4cf9-9197-36b4b962c643?action=share&creator=44577571

