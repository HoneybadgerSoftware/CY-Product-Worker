package com.honeybadgersoftware.productworker.service.impl;

import com.honeybadgersoftware.productworker.api.productservice.client.ProductServiceApi;
import com.honeybadgersoftware.productworker.api.productservice.model.CheckProductsExistenceRequest;
import com.honeybadgersoftware.productworker.api.productservice.model.NewProductData;
import com.honeybadgersoftware.productworker.api.productservice.model.UpdateNewProductsRequest;
import com.honeybadgersoftware.productworker.factory.ProductDataToSimplifiedProductFactory;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.model.ProductExistenceData;
import com.honeybadgersoftware.productworker.model.SimplifiedProductData;
import com.honeybadgersoftware.productworker.service.ProductSynchronizationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class ProductSynchronizationServiceImpl implements ProductSynchronizationService {

    private final ProductServiceApi productServiceApi;
    private final ProductDataToSimplifiedProductFactory simplifiedProductFactory;

    @Override
    public void synchronizeProducts(List<ProductData> product) {
        List<SimplifiedProductData> simplifiedProductData = simplifiedProductFactory.map(product);
        Pair<List<ProductExistenceData>, List<ProductExistenceData>> productsSortedByExistence =
                sortProductsByExistence(requireNonNull(productServiceApi.checkProductsExistence(
                        new CheckProductsExistenceRequest(simplifiedProductData)).getBody()).getProductExistenceData());
        List<ProductData> newProducts = extractNewProducts(product, productsSortedByExistence.getRight());

        productServiceApi.updateNewProducts()
    }

    private void sendProductsCreationRequest(List<ProductExistenceData> productExistenceData, List<ProductData> products) {

    }

    private List<NewProductData> extractNewProducts(
            List<ProductData> productData,
            List<ProductExistenceData> productExistenceData) {
        //TODO zrob to forem, bo musisz przemapować to , a streamem nie zachowasz informacji
        return productData.stream().filter(data ->
                        productExistenceData.stream()
                                .anyMatch(existenceData ->
                                        (existenceData.getName().equals(data.getName())
                                                && existenceData.getManufacturer().equals(data.getManufacturer()))))
                .map()
                .collect(Collectors.toList());
    }

    private Pair<List<ProductExistenceData>, List<ProductExistenceData>> sortProductsByExistence(
            List<ProductExistenceData> productExistenceData
    ) {
        List<ProductExistenceData> existingProducts = new ArrayList<>();
        List<ProductExistenceData> newProducts = new ArrayList<>();
        productExistenceData.forEach(data -> {
            if (data.isExistsInDb()) {
                existingProducts.add(data);
            }
            newProducts.add(data);
        });
        return Pair.of(existingProducts, newProducts);
    }
}
