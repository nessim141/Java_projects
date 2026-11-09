# Java Projects Portfolio

<p align="center">
  <img src="https://img.shields.io/badge/Java-Projects-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java projects" />
  <img src="https://img.shields.io/badge/JavaFX-Desktop%20Apps-2F6DB0?style=for-the-badge" alt="JavaFX" />
  <img src="https://img.shields.io/badge/Spring%20Boot-REST%20APIs-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
</p>

A collection of Java projects built during my engineering studies, covering desktop applications, REST APIs, database integration, and object-oriented design.

## Projects

| Project | Description | Technologies |
| --- | --- | --- |
| [**Hotel Reservation**](./hotelReservation) | Hotel, room, and reservation management domain model with services and repositories. | Java, OOP, service/repository patterns |
| [**Jeux de Mots**](./Jeux_De_Mots) | Word-game desktop application with a dictionary and interactive Java UI. | Java, JavaFX, SQL |
| [**Company Database Application**](./db_company_project) | JavaFX application with login, profiles, and database-backed user workflows. | Java, JavaFX, FXML, CSS, Maven |

## Repository structure

```text
Java_projects/
├── hotelReservation/      # Hotel reservation domain project
├── Jeux_De_Mots/          # Java word-game application
├── db_company_project/    # JavaFX company database application
└── README.md
```

## Running the Maven project

The JavaFX application is in `db_company_project`:

```bash
cd db_company_project
mvn clean javafx:run
```

Java source projects are kept intentionally independent so each can be explored on its own. Generated Maven output such as `target/` should remain local and is excluded by the repository ignore rules.

## About

These projects demonstrate my foundation in Java development while complementing my main focus on cloud infrastructure, DevOps automation, Kubernetes, and secure platform operations.
