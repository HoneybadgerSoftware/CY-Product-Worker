package com.honeybadgersoftware.productworker.controller;

import com.honeybadgersoftware.productworker.model.ProductData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productWorker")
public class ProductWorkerController {

    @PostMapping
    public void addSingleProduct(@RequestBody ProductData product){

    }
}
