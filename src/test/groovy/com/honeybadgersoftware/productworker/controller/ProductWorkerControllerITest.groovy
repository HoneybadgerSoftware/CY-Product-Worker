package com.honeybadgersoftware.productworker.controller


import com.honeybadgersoftware.productworker.base.BaseIntegrationTest
import com.honeybadgersoftware.productworker.model.ProductData
import com.honeybadgersoftware.productworker.model.SynchronizeProductsRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.honeybadgersoftware.productworker.data.ProductWorkerControllerITestData.getCHECK_PRODUCTS_REQUEST

class ProductWorkerControllerITest extends BaseIntegrationTest {

    def "Should return http 200 and response string"() {
        given:
        def product1 = new ProductData(
                name: "Fiji water 1lt",
                manufacturer: "XYZ Corp",
                description: "example description",
                price: new BigDecimal("19.99"),
                imageUrl: "http://example.com/test.jpg"
        )
        def product2 = new ProductData(
                name: "testProduct 1kg",
                manufacturer: "ABC Inc",
                description: "test description",
                price: new BigDecimal("99.99"),
                imageUrl: "http://example.com/test2.jpg"
        )
        def syncRequest = new SynchronizeProductsRequest(
                shopId: 123L,
                data: [product1, product2]
        )
        and:
        stubFor(post("http://localhost:8081/products/synchronize/check")
                .withRequestBody(equalToJson(CHECK_PRODUCTS_REQUEST))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(CHECK_PRODUCTS_REQUEST)))

        when:
        ResponseEntity<String> response = restTemplate.postForEntity(
                "http://localhost:$port/productWorker",
                syncRequest,
                String.class)

        then:
        response.getStatusCode() == HttpStatus.OK
        response.getBody() == "Products synchronization started"


    }
}
