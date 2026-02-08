📚 Library API – Spring Boot & Spring Data JPA

👋 About the Project
Library API is a backend application built with Spring Boot to demonstrate real-world data access patterns using Spring Data JPA.
This project was developed as part of a Udemy course, with a strong focus on persistence layer design, repository abstractions, and database interaction best practices commonly used in professional Java applications.

💼 Why This Project Matters
This project showcases practical skills that are highly relevant for Java backend roles, including:
Clean separation of domain, repository, and service layers
Effective use of Spring Data JPA
Writing maintainable and testable data access code
Implementing custom queries and search logic
Applying best practices for relational database persistence

🛠️ Tech Stack
Java 17
Spring Boot
Spring Data JPA
Hibernate
PostgreSQL
Maven
JUnit 5
Mockito
Docker

🧩 Key Features
CRUD operations for library resources
Repository interfaces using derived query methods
Custom JPQL queries
Pagination and filtering support
Integration with PostgreSQL
Repository-level automated tests

🧱 Architecture Overview
The application follows a layered architecture:
Controller → Service → Repository → Database
Domain Layer: JPA entities representing business models
Repository Layer: Spring Data JPA repositories
Service Layer: Business rules and orchestration
Test Layer: Focused repository tests ensuring query correctness

🧪 Testing Strategy
Repository tests using @DataJpaTest
Validation of custom queries and search behavior
Isolated database testing
Emphasis on reliability of the persistence layer

🗄️ Database Setup
The project uses PostgreSQL.
Helpful resources included in the repository:
comandos-sql.txt – SQL scripts for schema and data setup
comandos-docker.txt – Docker commands for running PostgreSQL locally

🚀 Getting Started
Prerequisites
Java 17+
Docker (recommended)
Maven
Running the Application
git clone https://github.com/your-username/libraryapi.git
cd libraryapi
./mvnw spring-boot:run

📌 What I Practiced in This Project
Designing repositories with Spring Data JPA
Creating efficient database queries
Testing persistence logic
Working with PostgreSQL in a Dockerized environment
Applying backend development best practices

📄 License
This project is licensed under the MIT License.
