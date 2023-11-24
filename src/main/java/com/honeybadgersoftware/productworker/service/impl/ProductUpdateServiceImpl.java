package com.honeybadgersoftware.productworker.service.impl;

import com.honeybadgersoftware.productworker.api.productservice.client.ProductServiceApi;
import com.honeybadgersoftware.productworker.api.productservice.model.data.NewProductUpdateData;
import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.api.productservice.model.request.UpdateNewProductsRequest;
import com.honeybadgersoftware.productworker.factory.context.FactoryContext;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.service.ProductUpdateService;
import com.honeybadgersoftware.productworker.utils.factory.ManyToOneFactory;
import com.honeybadgersoftware.productworker.utils.functions.Predicates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdateService {

    private final ProductServiceApi productServiceApi;
    private final FactoryContext factoryContext;

    @Override
    public void sendProductsCreationRequest(List<ProductData> productData, List<ProductExistenceData> productExistenceData) {
        productServiceApi.updateNewProducts(
                new UpdateNewProductsRequest(
                        createNewProductsUpdateData(productData, productExistenceData)));
    }

    private List<NewProductUpdateData> createNewProductsUpdateData(
            List<ProductData> productData,
            List<ProductExistenceData> productExistenceData) {

        ManyToOneFactory<ProductData, ProductExistenceData, NewProductUpdateData> factory =
                factoryContext.getFactory(NewProductUpdateData.class);
        return factory.map(productData, productExistenceData, Predicates.matchDataWithExistenceData);
    }
}
