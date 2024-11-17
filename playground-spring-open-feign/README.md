# Playground Spring Cloud Open Feign

[Reference documentation](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#spring-cloud-feign)

## Open Feign client generation

### `pom.xml`

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
            <!-- https://openapi-generator.tech/docs/generators/spring -->
            <configuration>
                <inputSpec>${project.basedir}/_outgoing-specs/httpstat.us.yml</inputSpec>
                <output>${project.basedir}/target/generated-sources</output>
                <apiPackage>io.positivinh.playground.spring.openfeign.httpstatus.client</apiPackage>
                <modelPackage>io.positivinh.playground.spring.openfeign.httpstatus.dtos</modelPackage>
                <generatorName>spring</generatorName>
                <library>spring-cloud</library>
                <generateModels>true</generateModels>
                <generateApiDocumentation>false</generateApiDocumentation>
                <generateApiTests>false</generateApiTests>
                <generateModelTests>false</generateModelTests>
                <ignoreFileOverride>${project.basedir}/.openapi-generator-ignore</ignoreFileOverride>
                <configOptions>
                    <generateApiTests>false</generateApiTests>
                    <generateModelTests>false</generateModelTests>
                    <useTags>true</useTags>
                    <performBeanValidation>true</performBeanValidation>
                    <useBeanValidation>true</useBeanValidation>
                    <useJakartaEe>true</useJakartaEe>
                    <useSpringBoot3>true</useSpringBoot3>
                    <useSpringController>true</useSpringController>
                    <delegatePattern>true</delegatePattern>
                    <useResponseEntity>false</useResponseEntity>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### Output

```java
@FeignClient(name="${httpStatus.name:httpStatus}", url="${httpStatus.url:https://httpstat.us}", configuration = ClientConfiguration.class)
public interface HttpStatusApiClient extends HttpStatusApi {
}
```


```java
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-11-17T11:23:04.824170+01:00[Europe/Luxembourg]")
@Validated
@RestController
@Tag(name = "http-status", description = "the http-status API")
public interface HttpStatusApi {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/200",
            produces = "text/plain"
    )
    @ResponseStatus(HttpStatus.OK)
    String get200();
}
```

## Wiremock

[Documentation](https://www.kevinhooke.com/2024/05/03/using-wiremock-with-spring-boot-3-and-junit-4/)
### Dependency

`pom.xml`

```xml

<dependency>
    <groupId>com.maciejwalkowiak.spring</groupId>
    <artifactId>wiremock-spring-boot</artifactId>
    <version>2.1.3</version>
    <scope>test</scope>
</dependency>
```

### Usage


```kotlin

@SpringBootTest
@EnableWireMock(
    ConfigureWireMock(name = "httpstatus-mock", properties = ["httpStatus.url"]) // httpStatus.url to configure HttpStatusApiClient
)
class HttpStatusApiClientTest {

    @Autowired
    private lateinit var httpStatusApiClient: HttpStatusApiClient

    @InjectWireMock("httpstatus-mock")
    private lateinit var wiremock: WireMockServer

    @Test
    fun callApi200() {

        wiremock.stubFor(
            WireMock.get("/200")
                .willReturn(WireMock.ok("200 OK"))
        )

        val res = httpStatusApiClient.get200()

        Assertions.assertEquals("200 OK", res)
    }
}
```
