package com.honeybadgersoftware.productworker.api.productservice.client;

import com.honeybadgersoftware.productworker.api.productservice.model.ProductExistenceResponse;
import com.honeybadgersoftware.productworker.api.productservice.model.CheckProductsExistenceRequest;
import com.honeybadgersoftware.productworker.api.productservice.model.UpdateNewProductsRequest;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "productService", url = "http://localhost:8081/products")
public interface ProductServiceApi {

    @PostMapping("/synchronize/check")
    ResponseEntity<ProductExistenceResponse> checkProductsExistence(
            @RequestBody CheckProductsExistenceRequest checkProductsExistenceRequest);

    @PutMapping("/synchronize/newProducts")
    ResponseEntity<Void> updateNewProducts(
            @RequestBody CheckProductsExistenceRequest checkProductsExistenceRequest);

}
