# Playground Spring HTTP interface client

[Reference documentation](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html#rest-http-interface)

## Spring HTTP interface client generation

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
                <apiPackage>io.positivinh.playground.spring.httpinterface.httpstatus.client</apiPackage>
                <modelPackage>io.positivinh.playground.spring.httpinterface.httpstatus.dtos</modelPackage>
                <generatorName>spring</generatorName>
                <library>spring-http-interface</library>
                <generateModels>true</generateModels>
                <generateApiDocumentation>false</generateApiDocumentation>
                <generateApiTests>false</generateApiTests>
                <generateModelTests>false</generateModelTests>
                <ignoreFileOverride>${project.basedir}/.openapi-generator-ignore</ignoreFileOverride>
                <templateDirectory>src/main/resources/mustache</templateDirectory>
                <configOptions>
                    <generateApiTests>false</generateApiTests>
                    <generateModelTests>false</generateModelTests>
                    <useTags>true</useTags>
                    <performBeanValidation>true</performBeanValidation>
                    <useBeanValidation>true</useBeanValidation>
                    <useJakartaEe>true</useJakartaEe>
                    <useSpringBoot3>true</useSpringBoot3>
                    <useSpringController>true</useSpringController>
                    <delegatePattern>false</delegatePattern>
                    <useResponseEntity>false</useResponseEntity>
                </configOptions>
            </configuration>
        </execution>
    </executions>
</plugin>
```

> Had to manually add `src/main/resources/mustache/pom-sb3.mustache`

### Output

```java
@FeignClient(name="${httpStatus.name:httpStatus}", url="${httpStatus.url:https://httpstat.us}", configuration = ClientConfiguration.class)
public interface HttpStatusApiClient extends HttpStatusApi {
}
```


```java
public interface HttpStatusApi {

    /**
     * GET /200
     *
     * @return default response (status code 200)
     */
    @HttpExchange(
        method = "GET",
        value = "/200",
        accept = { "text/plain" }
    )
    String get200();
}
```

### Client configuration

```kotlin
@Configuration
class HttpStatusApiClientConfiguration(val httpStatusApiClientConfigurationProperties: HttpStatusApiClientConfigurationProperties) {


    @Bean
    fun httpStatusApiClient(): HttpStatusApi {

        val webClient = WebClient.builder()
            .baseUrl(httpStatusApiClientConfigurationProperties.url)
            .build()

        val httpServiceProxyFactory = HttpServiceProxyFactory
            .builderFor(WebClientAdapter.create(webClient))
            .build()

        return httpServiceProxyFactory.createClient(HttpStatusApi::class.java)
    }
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
    private lateinit var httpStatusApiClient: HttpStatusApi

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
