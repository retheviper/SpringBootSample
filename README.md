# Spring Boot Sample

## TL;DR

A simple sample of Rest API by [Spring Boot](https://spring.io/projects/spring-boot) with Java 11.

## Includes

- [Gradle](https://gradle.org) Multi Project (With [Groovy](https://groovy-lang.org/))
- [Lombok](https://projectlombok.org)
- [JUnit 5](https://junit.org/junit5) Tests
- [Spring Security](https://spring.io/projects/spring-security) with [JWT](https://jwt.io)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) with [H2](https://www.h2database.com) (Memory Mode)

## Architecture

- `web`: Remoting layer
  - `config`: Configuration classes
  - `controller`: Rest controllers
  - `form`: Request bodies
  - `interceptor`: Interceptors
  - `security`: Utilities for security
  - `viewmodel`: Response bodies
- `domain`: Business logic and data access layer
  - `application`: Business logics
    - `config`: Configuration classes
    - `dto`: DTOs
    - `sevice`: Service classes
    - `common`: Constants, exception, utilities
  - `domain`: Data access
    - `entity`: Entities
    - `repository`: Repositories
  
## Test APIs

1. Run server.

```shell
./gradlew bootRun
```

2. Import [Postman](https://www.postman.com) data from below.

```shell
/miscs/SpringBootSample.postman_collection.json
```

3. Test with Postman. 

Note: Some APIs need JWT on HTTP Request Header(`X-AUTH-TOKEN`) and it will be found at HTTP Response Header when successfully logged in.

## Swagger

```shell
http://localhost:8080/api/v1/web/swagger-ui/index.html
```
