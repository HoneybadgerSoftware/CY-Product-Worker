package com.honeybadgersoftware.productworker.base

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


    def setup() {
        addressToUseForTests = 'http://localhost:' + port
    }

}
