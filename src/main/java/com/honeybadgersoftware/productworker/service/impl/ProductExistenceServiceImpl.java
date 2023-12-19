package com.honeybadgersoftware.productworker.service.impl;

import com.honeybadgersoftware.productworker.api.productservice.client.ProductServiceApi;
import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.api.productservice.model.request.CheckProductsExistenceRequest;
import com.honeybadgersoftware.productworker.api.productservice.model.response.ProductExistenceResponse;
import com.honeybadgersoftware.productworker.factory.ProductDataToSimplifiedProductFactory;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.model.SimplifiedProductData;
import com.honeybadgersoftware.productworker.service.ProductExistenceService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class ProductExistenceServiceImpl implements ProductExistenceService {

    private final ProductServiceApi productServiceApi;
    private final ProductDataToSimplifiedProductFactory simplifiedProductFactory;

    @Override
    public Pair<List<ProductExistenceData>, List<ProductExistenceData>> getSortedProducts(List<ProductData> products) {
        return sortProductsByExistence(
                requireNonNull(checkProductsExistence(simplifiedProductFactory.map(products)).getData()));
    }

    private Pair<List<ProductExistenceData>, List<ProductExistenceData>> sortProductsByExistence(
            List<ProductExistenceData> productExistenceData) {
        List<ProductExistenceData> existingProducts = new ArrayList<>();
        List<ProductExistenceData> newProducts = new ArrayList<>();
        productExistenceData.forEach(data -> {
            if (data.isExistsInDb()) {
                existingProducts.add(data);
            } else {
                newProducts.add(data);
            }
        });
        return Pair.of(existingProducts, newProducts);
    }

    private ProductExistenceResponse checkProductsExistence(List<SimplifiedProductData> simplifiedProductData) {
        return productServiceApi.checkProductsExistence(
                new CheckProductsExistenceRequest(simplifiedProductData)).getBody();
    }
}
