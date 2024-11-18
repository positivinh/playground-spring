package io.positivinh.playground.spring.httpinterface.httpstatus.client

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.web.client.HttpClientErrorException

@SpringBootTest
@TestPropertySource(
    properties = [
        "http-status.client.url=http://httpstat.us",
    ]
)
class HttpStatusClientApiIT {

    @Autowired
    private lateinit var httpStatusApiClient: HttpStatusApi

    @Test
    fun callApi200() {

        val res = httpStatusApiClient.get200("test header", 0, "test body")

        Assertions.assertEquals("200 OK", res)
    }

    @Test
    fun callApi400() {

        Assertions.assertThrows(HttpClientErrorException.BadRequest::class.java) { httpStatusApiClient.get400() }
    }
}
