### Description
* This service create short url for a long url.

### Run and Test

* Clone the repository and run the application with
 `mvn spring-boot:run` command.
* Post `\api\save` endpoint is implemented to create short url. Its required to pass a long url as
a parameter. It will return encoded short Url.
* Get `\api\get` endpoint is implemented to get original long url based
on encoded short url generate by hitting above endpoint.

### Tech Stack
* Kotlin - 1.6.21
* Java - 11
* Spring Boot - 2.7.1
* H2 DB