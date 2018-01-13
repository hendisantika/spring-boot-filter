# Spring Boot Filter

This is an example of using filter in Spring Boot with an external library

Run this repo by `mvn clean spring-boot:run`

Show All Data :

`http://localhost:8080/customers`

Filtering by a single attribute

`http://localhost:8080/customers?firstName=Homer`

Filtering by multiple attributes

`http://myhost/api/customers?firstName=Homer&lastName=Simpson`

This repo is coming from [this web.](http://blog.kaczmarzyk.net/2014/03/23/alternative-api-for-filtering-data-with-spring-mvc-and-spring-data/)