package com.honeybadgersoftware.productworker.utils.functions;

import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.model.ProductData;

import java.util.function.BiPredicate;

public class Predicates {
    public static final BiPredicate<ProductData, ProductExistenceData> matchDataWithExistenceData =
            (data, existenceData) -> (
                    existenceData.getName().equals(data.getName())
                            && existenceData.getManufacturer().equals(data.getManufacturer()));
}
