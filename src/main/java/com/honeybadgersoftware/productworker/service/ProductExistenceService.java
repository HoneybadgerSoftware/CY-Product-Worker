package com.honeybadgersoftware.productworker.service;

import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.model.ProductData;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface ProductExistenceService {

    Pair<List<ProductExistenceData>, List<ProductExistenceData>> getSortedProducts(List<ProductData> products);
}
