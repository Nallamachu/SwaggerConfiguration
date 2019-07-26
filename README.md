# SwaggerConfiguration
Swagger configuration in Spring Boot Application
To configure ```swagger``` in any of the ```SpringBoot``` application, we need to follow the below steps explained.
1. We need to add required dependencies to the ```pom.xml``` file. Required dependencies mentioned below,

**pom.xml**
```xml
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger2</artifactId>
			<version>2.9.2</version>
			<scope>compile</scope>
		</dependency>
		<dependency>
			<groupId>io.springfox</groupId>
			<artifactId>springfox-swagger-ui</artifactId>
			<version>2.9.2</version>
			<scope>compile</scope>
		</dependency>
```
2. Create a class with any name under the config package and annotate with the ```@Configuration``` and ```@EnableSwagger2```. I am using the name of ```SwaggerConfig```, as mentioned below,

**Swagger2Config.java**
```java
@Configuration
@EnableSwagger2
public class Swagger2Config {

}
```

3. We need to create a bean of Docket. Docket can select all the API's under the mentioned package. Look at the below code,
```java
	@Bean
	public Docket produceApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.springboot.swagger.controller"))
				.build();
	}
```
**Note:** Swagger configuration has been completed. Please make sure you are passing proper package name of the controller. If you have already existing controllers with any rest api, it will be loaded when you are starting/restarting the application.

You can access those API's through the link called, *http://<host>:<port>/v2/api-docs* with the default URL of *http://localhost:8080/v2/api-docs *

We can also access the Swagger-UI with the link of *http://<host>:<port>/swagger-ui.html* with the default URL of *http://localhost:8080/swagger-ui.html *
	
If you don't have any controllers defined in your application. For temporary you can use the below controller classes.

**HelloController.java**
```java
package com.springboot.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(path = "/hello")
	public String hello() {
		return "Hello";
	}
	
	@GetMapping(path = "/hello-name")
	public String helloWithParam(@RequestParam String name) {
		return "Hello "+name;
	}
}
```
![Swagger-UI](https://github.com/Nallamachu/SwaggerConfiguration/blob/master/swagger-ui.PNG)

If you would like to allow swagger to scan across all other packages in your project, you can use the below code while creating the ```Docket``` in ```SwaggerConfig```.
```java
@Bean
public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
}
```
