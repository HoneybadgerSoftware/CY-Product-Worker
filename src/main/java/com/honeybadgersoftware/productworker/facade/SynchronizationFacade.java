package com.honeybadgersoftware.productworker.facade;

import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.model.SynchronizeProductsRequest;
import com.honeybadgersoftware.productworker.service.ProductAvailabilityService;
import com.honeybadgersoftware.productworker.service.ProductExistenceService;
import com.honeybadgersoftware.productworker.service.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SynchronizationFacade {

    private final ProductExistenceService productExistenceService;
    private final ProductUpdateService productUpdateService;
    private final ProductAvailabilityService productAvailabilityService;

    public void synchronize(SynchronizeProductsRequest synchronizeProductsRequest) {

        List<ProductData> productsData = synchronizeProductsRequest.getData();

        Pair<List<ProductExistenceData>, List<ProductExistenceData>> productsSortedByExistence
                = productExistenceService.getSortedProducts(productsData);

        if (!productsSortedByExistence.getRight().isEmpty()) {
            productUpdateService.sendProductsCreationRequest(productsData, productsSortedByExistence.getRight());
        }
        productAvailabilityService.sendProductAvailabilityRequest(productsSortedByExistence, synchronizeProductsRequest);
    }
}
