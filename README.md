# Spring Boot Sample

## TL;DR

A simple sample of Rest API by Spring Boot with Java 11.

## Includes

- Gradle Multi Project (With Groovy)
- Lombok
- JUnit 5 Tests
- Spring Security with JWT
- Spring Data JPA with H2

## Architecture

- web: MVC/Remoting layer
  - config: configuration classes
  - controller: rest controllers
  - form: request bodies
  - interceptor: interceptors
  - security: utilities for security
  - viewmodel: response bodies
- domain: business logic and data access layer
  - application: business logics
    - config: configuration classes
    - dto: dtos
    - sevice: service classes
    - common: constants, exception, utilities
  - domain: data access
    - entity: entities
    - repository: repositories
  
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