### Description
* This service create short url for a long url.

### Run and Test

* Clone the repository and run the application with
 `mvn spring-boot:run` command.
* Post `/api/save` endpoint is implemented to create short url. Its required to pass a long url as
a parameter. It will return encoded short Url. As part of the solution to generate unique key for long ur, google
Hashing library is used.
* Get `/api/get` endpoint is implemented to get original long url based
on encoded short url generate by hitting above endpoint.
* Test coverages for Controller is also implemented. 

### Tech Stack
* Kotlin - 1.6.21
* Java - 11
* Spring Boot - 2.7.1
* H2 DB
* JUnit 5

### Author
* Ravinder Kumar