package com.honeybadgersoftware.productworker.api.productservice.client;

import com.honeybadgersoftware.productworker.api.productservice.model.request.CheckProductsExistenceRequest;
import com.honeybadgersoftware.productworker.api.productservice.model.request.UpdateNewProductsRequest;
import com.honeybadgersoftware.productworker.api.productservice.model.response.ProductExistenceResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "productService", url = "http://localhost:8081/products")
public interface ProductServiceApi {

    @PostMapping("/synchronize/check")
    @Headers("Content-Type: application/json")
    ResponseEntity<ProductExistenceResponse> checkProductsExistence(
            @RequestBody CheckProductsExistenceRequest checkProductsExistenceRequest);

    @PutMapping("/synchronize/newProducts")
    @Headers("Content-Type: application/json")
    ResponseEntity<Void> updateNewProducts(
            @RequestBody UpdateNewProductsRequest checkProductsExistenceRequest);

}
