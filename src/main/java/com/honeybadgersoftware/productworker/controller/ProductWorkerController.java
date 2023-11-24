package com.honeybadgersoftware.productworker.controller;

import com.honeybadgersoftware.productworker.facade.SynchronizationFacade;
import com.honeybadgersoftware.productworker.model.SynchronizeProductsRequest;
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

    private final SynchronizationFacade synchronizationFacade;

    @PostMapping
    public ResponseEntity<String> synchronizeProducts(@RequestBody SynchronizeProductsRequest products) {
        synchronizationFacade.synchronize(products);
        return ResponseEntity.ok("Products synchronization started");
    }
}
