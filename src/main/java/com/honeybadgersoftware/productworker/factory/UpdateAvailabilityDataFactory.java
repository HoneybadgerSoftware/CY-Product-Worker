package com.honeybadgersoftware.productworker.factory;

import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityData;
import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.utils.ManyToOneFactory;


public class UpdateAvailabilityDataFactory implements ManyToOneFactory<ProductData, ProductExistenceData, UpdateAvailabilityData> {


    @Override
    public UpdateAvailabilityData map(ProductData productData, ProductExistenceData existenceData) {
        return new UpdateAvailabilityData(
                existenceData.getId(),
                productData.getPrice());
    }
}
