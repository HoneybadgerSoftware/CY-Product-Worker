package com.honeybadgersoftware.productworker.base

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.Options
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.TestPropertySource
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
abstract class BaseIntegrationTest extends Specification {

    @LocalServerPort
    protected int port

    @Shared
    protected String addressToUseForTests

    @Autowired
    protected TestRestTemplate restTemplate

    WireMockServer wireMock = new WireMockServer(8081)

    def setup() {
        addressToUseForTests = 'http://localhost:' + port
        wireMock.start()
    }

    def cleanup(){
        wireMock.stop()
    }

}
