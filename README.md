# springboot-crud-demo

Spring Boot CRUD demo is demonstrating how to implement simple CRUD operations with a `Product` entity.

## What's inside 
This project is based on the [Spring Boot](http://projects.spring.io/spring-boot/) project and uses these packages :
- Maven
- Spring Core
- Spring Data (Hibernate & MySQL)
- Swagger UI

## Installation 
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

## Database configuration 
Create a MySQL database with the name `springbootdb` and add the credentials to `/resources/application.properties`.  
The default ones are :

```
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/university
spring.datasource.username=root
spring.datasource.password=my-secret-pw
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## Usage 
Run the project through the IDE and head out to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

or 

run this command in the command line:
```
mvn spring-boot:run
```
