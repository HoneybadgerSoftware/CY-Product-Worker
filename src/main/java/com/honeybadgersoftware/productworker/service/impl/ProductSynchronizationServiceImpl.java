package com.honeybadgersoftware.productworker.service.impl;

import com.honeybadgersoftware.productworker.api.availabilityService.client.AvailabilityServiceApi;
import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityData;
import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityRequest;
import com.honeybadgersoftware.productworker.api.productservice.client.ProductServiceApi;
import com.honeybadgersoftware.productworker.api.productservice.model.data.NewProductUpdateData;
import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.api.productservice.model.request.CheckProductsExistenceRequest;
import com.honeybadgersoftware.productworker.api.productservice.model.request.UpdateNewProductsRequest;
import com.honeybadgersoftware.productworker.api.productservice.model.response.ProductExistenceResponse;
import com.honeybadgersoftware.productworker.factory.ProductDataToSimplifiedProductFactory;
import com.honeybadgersoftware.productworker.factory.context.FactoryContext;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.model.SimplifiedProductData;
import com.honeybadgersoftware.productworker.model.SynchronizeProductsRequest;
import com.honeybadgersoftware.productworker.service.ProductSynchronizationService;
import com.honeybadgersoftware.productworker.utils.ManyToOneFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class ProductSynchronizationServiceImpl implements ProductSynchronizationService {

    private final ProductServiceApi productServiceApi;
    private final AvailabilityServiceApi availabilityServiceApi;
    private final ProductDataToSimplifiedProductFactory simplifiedProductFactory;

    private final FactoryContext factoryContext;

    @Override
    public void synchronizeProducts(SynchronizeProductsRequest products) {

        List<ProductData> productsData = products.getData();
        List<SimplifiedProductData> simplifiedProductData = simplifiedProductFactory.map(productsData);

        Pair<List<ProductExistenceData>, List<ProductExistenceData>> productsSortedByExistence =
                sortProductsByExistence(
                        requireNonNull(checkProductsExistence(simplifiedProductData).getData())
                );

        if (!productsSortedByExistence.getRight().isEmpty()) {
            List<NewProductUpdateData> newProducts =
                    createNewProductsUpdateData(productsData, productsSortedByExistence.getRight());
            sendProductsCreationRequest(newProducts);
        }

        sendProductAvailabilityRequest(createProductAvailabilityUpdateRequest(productsSortedByExistence, products));
    }

    private Pair<List<ProductExistenceData>, List<ProductExistenceData>> sortProductsByExistence(
            List<ProductExistenceData> productExistenceData) {
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

    private ProductExistenceResponse checkProductsExistence(List<SimplifiedProductData> simplifiedProductData) {
        return productServiceApi.checkProductsExistence(
                new CheckProductsExistenceRequest(simplifiedProductData)).getBody();
    }

    private void sendProductsCreationRequest(List<NewProductUpdateData> newProductUpdateDatum1s) {
        productServiceApi.updateNewProducts(new UpdateNewProductsRequest(newProductUpdateDatum1s));
    }

    private List<NewProductUpdateData> createNewProductsUpdateData(
            List<ProductData> productData,
            List<ProductExistenceData> productExistenceData) {

        ManyToOneFactory<ProductData, ProductExistenceData, NewProductUpdateData> factory =
                factoryContext.getFactory(NewProductUpdateData.class);
        return factory.map(productData, productExistenceData, matchDataWithExistenceData);
    }

    private void sendProductAvailabilityRequest(UpdateAvailabilityRequest updateAvailabilityRequest) {
        availabilityServiceApi.updateAvailability(updateAvailabilityRequest);
    }

    private UpdateAvailabilityRequest createProductAvailabilityUpdateRequest(
            Pair<List<ProductExistenceData>, List<ProductExistenceData>> productsSortedByExistence,
            SynchronizeProductsRequest synchronizeProductsRequest) {
        List<ProductData> productData = synchronizeProductsRequest.getData();
        return new UpdateAvailabilityRequest(
                synchronizeProductsRequest.getShopId(),
                prepareUpdateAvailabilityData(productData, productsSortedByExistence.getLeft()),
                prepareUpdateAvailabilityData(productData, productsSortedByExistence.getRight())
        );
    }

    private List<UpdateAvailabilityData> prepareUpdateAvailabilityData(
            List<ProductData> productData,
            List<ProductExistenceData> productExistenceData) {

        ManyToOneFactory<ProductData, ProductExistenceData, UpdateAvailabilityData> factory =
                factoryContext.getFactory(UpdateAvailabilityData.class);
        return factory.map(productData, productExistenceData, matchDataWithExistenceData);
    }

    private final BiPredicate<ProductData, ProductExistenceData> matchDataWithExistenceData =
            (data, existenceData) -> (
                    existenceData.getName().equals(data.getName())
                            && existenceData.getManufacturer().equals(data.getManufacturer()));
}
