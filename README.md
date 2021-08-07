# Geekseat Witch Saga: Return of The Coder!

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Installation Steps


1. ##### Build the source code
```mvn clean install```


2. ##### Run the created jar file 
```java -jar geekseatwitchsaga-1.0.0.jar```

#### Running the application locally


Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```


  </br>


### Accessing the application

```http://localhost:8080/witchsaga```

### Accessing the REST Service

```http://localhost:8080/witchsaga/expelwitch```

Request Example:</br>
Expel Witch
```
curl --location --request POST 'http://localhost:8080/witchsaga/expelwitch' \
--header 'Content-Type: application/json' \
--data-raw '[{
    "ageOfDeath" : 10,
    "yearOfDeath" : 12
}, {
    "ageOfDeath" : 13,
    "yearOfDeath" : 17
}]'
