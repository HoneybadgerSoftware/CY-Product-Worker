package com.honeybadgersoftware.productworker.controller;

import com.honeybadgersoftware.productworker.model.SynchronizeProductsRequest;
import com.honeybadgersoftware.productworker.service.impl.ProductSynchronizationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productWorker")
@RequiredArgsConstructor
public class ProductWorkerController {

    private final ProductSynchronizationServiceImpl productSynchronizationService;

    @PostMapping
    public ResponseEntity<String> synchronizeProducts(@RequestBody SynchronizeProductsRequest products) {
        productSynchronizationService.synchronizeProducts(products);
        return ResponseEntity.ok("Products synchronization started");
    }
}
