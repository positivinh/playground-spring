package io.positivinh.playground.spring.httpinterface.httpstatus.client

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock
import com.maciejwalkowiak.wiremock.spring.EnableWireMock
import com.maciejwalkowiak.wiremock.spring.InjectWireMock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.client.HttpClientErrorException


@SpringBootTest
@EnableWireMock(
    ConfigureWireMock(name = "httpstatus-mock", properties = ["http-status.client.url"])
)
class HttpStatusApiClientTest {

    @Autowired
    private lateinit var httpStatusApiClient: HttpStatusApi

    @InjectWireMock("httpstatus-mock")
    private lateinit var wiremock: WireMockServer

    @Test
    fun callApi200() {

        wiremock.stubFor(
            WireMock.get(WireMock.urlPathMatching("/200(\\?sleep)?"))
                .willReturn(WireMock.ok("200 OK"))
        )

        val res = httpStatusApiClient.get200("test header", 0, "test body")

        Assertions.assertEquals("200 OK", res)

        val res2 = httpStatusApiClient.get200(null, null, null)
        Assertions.assertEquals("200 OK", res2)
    }

    @Test
    fun callApi400() {

        wiremock.stubFor(
            WireMock.get("/400")
                .willReturn(WireMock.badRequest())
        )

        Assertions.assertThrows(HttpClientErrorException.BadRequest::class.java) { httpStatusApiClient.get400() }
    }
}
