package io.positivinh.playground.spring.openfeign.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock
import com.maciejwalkowiak.wiremock.spring.EnableWireMock
import com.maciejwalkowiak.wiremock.spring.InjectWireMock
import feign.FeignException
import io.positivinh.playground.spring.openfeign.httpstatus.client.HttpStatusApiClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


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

    @Test
    fun callApi400() {

        wiremock.stubFor(
            WireMock.get("/400")
                .willReturn(WireMock.badRequest())
        )

        Assertions.assertThrows(FeignException.BadRequest::class.java) { httpStatusApiClient.get400() }
    }
}
