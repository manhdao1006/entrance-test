# Entrance Test Intern Java

## Project Overview
This project uses the following technologies:
- **Java Spring Boot**: Version 3.3.4
- **JDK**: Version 17
- **Database**: MySQL

## Setup Instructions
If this is the first time running the project, follow these steps:

1. Open the `application.properties` file.
2. Change the properties:
    ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
4. Change the following property:
    ```bash
    from
      spring.jpa.hibernate.ddl-auto=update
    to
      spring.jpa.hibernate.ddl-auto=create-drop
