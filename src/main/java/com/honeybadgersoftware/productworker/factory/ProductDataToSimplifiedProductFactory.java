package com.honeybadgersoftware.productworker.factory;

import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.model.SimplifiedProductData;
import com.honeybadgersoftware.productworker.utils.OneToOneFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDataToSimplifiedProductFactory implements OneToOneFactory<ProductData, SimplifiedProductData> {

    @Override
    public SimplifiedProductData map(ProductData productData) {
        return SimplifiedProductData.builder()
                .productName(productData.getName())
                .manufacturer(productData.getManufacturer())
                .price(productData.getPrice())
                .build();
    }

}
