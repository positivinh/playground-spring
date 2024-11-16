package io.positivinh.playground.spring.openfeign.client

import feign.FeignException
import io.positivinh.playground.spring.openfeign.httpstatus.client.HttpStatusApiClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HttpStatusApiClientIT {

    @Autowired
    private lateinit var httpStatusApiClient: HttpStatusApiClient

    @Test
    fun callApi200() {

        val res = httpStatusApiClient.get200()

        Assertions.assertEquals("200 OK", res)
    }

    @Test
    fun callApi400() {

       Assertions.assertThrows(FeignException.BadRequest::class.java) { httpStatusApiClient.get400() }
    }
}
