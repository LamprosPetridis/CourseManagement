# Documentation
This project aims to build a course managament system for use in universities

* Version of Java used is 11
* Includes support for Swagger
* By default the maximum number of course a student can attend is 5
* This can be changed through 'application.properties' file

# Testing Application

### Postman testing
In order to test using Postman, there is an included 'CourseManagement.postman_collection.json' file which contains all requests supported currently

### Swagger testing
Swagger is enabled and can be accessed through the following link
* [Swagger-UI](http://localhost:8080/swagger-ui/)

### H2 Database
This application makes use of H2 Database in order to store data. Database can be accessed throuth the folllowing link
* [H2-Database](http://localhost:8080/h2-console/)
#### Default configuration is:
* JDBC URL:	'jdbc:h2:mem:testdb'
* User Name: 'sa'
* Password: empty, no password needed

# Export to Excel
Follow link below to export all student entries in database to Excel
* [Download Excel](http://localhost:8080/student/export-to-excel)
