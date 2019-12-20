# JWT Spring Security Demo Application

## About
This is a demo for using **JWT (JSON Web Token)** with **[Spring Security](https://spring.io/projects/spring-security)** and
**[Spring Boot](https://spring.io/projects/spring-boot)**. I completely wrote my first version.


## Requirements
This demo is build with with Maven  and Java 8.

## Usage
Just start the application with the Spring Boot maven plugin (`mvn spring-boot:run`). The application is
running at [http://localhost:8081](http://localhost:8081).


## Backend
you can defined more than one user accounts present to demonstrate the different levels of access to the endpoints in
the API and the different authorization exceptions like :
```
- Admin 
- Employee 
  and so on ....
```
**Swagger Documentation**

    you can access from url: /swagger-ui.html

**Generics Exception Handling**

### Using  database

Actually this demo is using an mysql database that is manually configured . If you want to connect 
to another database you have to specify the connection in the *application.properties* in the resource directory. Here configuration for a MySQL DB:

```
    spring.datasource.url=jdbc:mysql://localhost:3306/t
    spring.datasource.username= <your username>
    spring.datasource.password= <your password>
       # possible values: validate | update | create | create-drop
    spring.jpa.hibernate.ddl-auto= update
    spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.MySQL5Dialect
    spring.datasource.driver-class-name= com.mysql.jdbc.Driver
```

You can find a reference of all application properties [here](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html).

## Author

**Emadelddin Juha**
* https://github.com/emadjoha

---------------------------------------

Please feel free to send me some feedback or questions!
