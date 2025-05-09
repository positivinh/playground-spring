# Playground Spring Web MVC

# Technologies

- [Spring Web MVC](README/spring-webmvc.md)
- [Open API 3 code generator](README/oas3-generation.md)
- [Spring Security](https://docs.spring.io/spring-security/reference/servlet/configuration/java.html)

# Run maven build

```shell
mvn clean verify -f ../pom.xml -am -pl playground-spring-webmvc
```

# API client

Test HTTP client requests are available [here](./playground-spring-webmvc.http)

# Spring security

[Reference documentation](https://docs.spring.io/spring-security/reference/servlet/configuration/kotlin.html)

## Configuration

```kotlin
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
class SpringSecurityConfiguration {

    @Bean
    fun securityFilterChain(http: HttpSecurity, authorizationTokenFilter: AuthorizationTokenFilter): SecurityFilterChain {
        http {
            csrf { disable() }
            authorizeHttpRequests {
                authorize(anyRequest, authenticated)
            }
            addFilterBefore<UsernamePasswordAuthenticationFilter>(authorizationTokenFilter)
        }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {

        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = listOf("*")
        corsConfiguration.allowedMethods = listOf("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration)

        return source
    }
}
```

## Authorization

```kotlin
@Component
class ResourceFacade(private val resourceService: ResourceService) : ResourceApiDelegate {

    private val resourceMapper = Mappers.getMapper(ResourceMapper::class.java)

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    override fun createResource(resource: Resource): Resource {

        return resourceService.createResource(resource)
    }
}
```

## Actuator

[Reference documentation](https://docs.spring.io/spring-boot/reference/actuator/endpoints.html#page-title)

### Dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```xml

<plugin>
    <groupId>io.github.git-commit-id</groupId>
    <artifactId>git-commit-id-maven-plugin</artifactId>
</plugin>
```

### Configuration

`application.yml`

```yaml
management:
    endpoints:
        web:
            base-path: /actuator
            exposure:
                include:
                    #          - * # enable all actuator endpoints
                    - health
                    - info
                    - beans
                    - conditions
                    - configprops
                    - env
                    - loggers
                    - metrics
                    - scheduledtasks
                    - httpexchanges
                    - metrics
                    - mappings
                    - logfile
    endpoint:
        health:
            group:
                custom:
                    show-components: always
                    show-details: always
                    include:
                        - diskSpace
                        - ping

```
