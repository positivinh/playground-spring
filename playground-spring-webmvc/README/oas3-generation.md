# OpenAPI specification 3 code generation

- https://openapi-generator.tech/docs/generators/spring
- https://springdoc.org/v2/

## Properties

```xml
<properties>
    <openapi.codegen.version>6.6.0</openapi.codegen.version>
    <springdoc-openapi.version>2.1.0</springdoc-openapi.version>
    <swagger-v3.version>2.2.11</swagger-v3.version>
</properties>
```

## Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>com.fasterxml.jackson.module</groupId>
        <artifactId>jackson-module-kotlin</artifactId>
    </dependency>
    <dependency>
        <groupId>org.openapitools</groupId>
        <artifactId>jackson-databind-nullable</artifactId>
        <version>0.2.3</version>
    </dependency>
    <dependency>
        <groupId>io.swagger.core.v3</groupId>
        <artifactId>swagger-annotations</artifactId>
        <version>${swagger-v3.version}</version>
    </dependency>
    <dependency>
        <groupId>io.swagger.core.v3</groupId>
        <artifactId>swagger-models</artifactId>
        <version>${swagger-v3.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-api</artifactId>
        <version>${springdoc-openapi.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>${springdoc-openapi.version}</version>
    </dependency>
    <dependency>
        <groupId>org.hibernate.validator</groupId>
        <artifactId>hibernate-validator</artifactId>
    </dependency>
</dependencies>
```

## Maven plugin

```xml
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <version>${openapi.codegen.version}</version>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>${project.basedir}/_api-docs/${project.artifactId}.yaml</inputSpec>
                <output>${project.basedir}/target/generated-sources</output>
                <apiPackage>io.positivinh.playground.spring.webmvc.controllers</apiPackage>
                <modelPackage>io.positivinh.playground.spring.webmvc.dtos</modelPackage>
                <generatorName>spring</generatorName>
                <library>spring-boot</library>
                <generateModels>true</generateModels>
                <generateApiDocumentation>false</generateApiDocumentation>
                <configOptions>
                    <useTags>true</useTags>
                    <sourceFolder>src/gen/java/main</sourceFolder>
                    <interfaceOnly>true</interfaceOnly>
                    <dateLibrary>java8</dateLibrary>
                    <performBeanValidation>true</performBeanValidation>
                    <useBeanValidation>true</useBeanValidation>
                    <annotationLibrary>swagger2</annotationLibrary>
                    <useJakartaEe>true</useJakartaEe>
                    <useSpringBoot3>true</useSpringBoot3>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### Useful properties

| property       | usage                                        |
|----------------|----------------------------------------------|
| generatorName  | define the generator to use                  |
| library        | choose the library to use for the generation |
| useSpringBoot3 | support for Spring Boot 3                    |
|                |                                              |
