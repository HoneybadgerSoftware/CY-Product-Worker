package com.honeybadgersoftware.productworker.service.impl;

import com.honeybadgersoftware.productworker.api.availabilityService.client.AvailabilityServiceApi;
import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityData;
import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityRequest;
import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.factory.context.FactoryContext;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.model.SynchronizeProductsRequest;
import com.honeybadgersoftware.productworker.service.ProductAvailabilityService;
import com.honeybadgersoftware.productworker.utils.factory.ManyToOneFactory;
import com.honeybadgersoftware.productworker.utils.functions.Predicates;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAvailabilityServiceImpl implements ProductAvailabilityService {

    private final AvailabilityServiceApi availabilityServiceApi;
    private final FactoryContext factoryContext;

    public void sendProductAvailabilityRequest(
            Pair<List<ProductExistenceData>, List<ProductExistenceData>> productsSortedByExistence,
            SynchronizeProductsRequest synchronizeProductsRequest) {
        availabilityServiceApi.updateAvailability(createProductAvailabilityUpdateRequest(
                productsSortedByExistence, synchronizeProductsRequest));
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
        return factory.map(productData, productExistenceData, Predicates.matchDataWithExistenceData);
    }
}
