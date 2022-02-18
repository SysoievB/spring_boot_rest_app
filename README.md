# spring_boot_rest_app
Spring REST Api with DB & CRUD operations with such entities:

Customer (id, name, surname, Status status, Set<Order>)

Order (id, name)

Account (id, Status status)

enum Status {... ACTIVE, DELETED, BANNED ...}

Requirements:

All CRUD operations for every entity

MVC pattern

Use Maven & Spring (IoC, Data, etc.)

For connection with DB use - Spring Data

Initializing DB should be with liquibase

User interaction needs to be implemented with Postman (https://www.getpostman.com/)

Technologies: Java 11, MySQL, Spring (MVC, Web, Data, Boot), Lombok, Maven, Liquibase, Testing with Mockito & JUnit.
