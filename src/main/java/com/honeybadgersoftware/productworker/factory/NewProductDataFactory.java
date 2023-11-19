package com.honeybadgersoftware.productworker.factory;

import com.honeybadgersoftware.productworker.api.productservice.model.data.NewProductUpdateData;
import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.utils.ManyToOneFactory;
import org.springframework.stereotype.Component;


@Component
public class NewProductDataFactory implements ManyToOneFactory<ProductData, ProductExistenceData, NewProductUpdateData> {

    @Override
    public NewProductUpdateData map(ProductData productData, ProductExistenceData existenceData) {
        return new NewProductUpdateData(
                existenceData.getId(),
                productData.getPrice(),
                productData.getDescription(),
                productData.getImageUrl());
    }
}
