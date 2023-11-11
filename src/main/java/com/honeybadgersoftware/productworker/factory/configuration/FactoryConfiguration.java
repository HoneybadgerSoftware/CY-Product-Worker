package com.honeybadgersoftware.productworker.factory.configuration;

import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityData;
import com.honeybadgersoftware.productworker.api.productservice.model.data.NewProductUpdateData;
import com.honeybadgersoftware.productworker.factory.NewProductDataFactory;
import com.honeybadgersoftware.productworker.factory.UpdateAvailabilityDataFactory;
import com.honeybadgersoftware.productworker.model.ProductData;
import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import com.honeybadgersoftware.productworker.utils.ManyToOneFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class FactoryConfiguration {

    @Bean
    public ManyToOneFactory<ProductData, ProductExistenceData, NewProductUpdateData> newProductDataFactory() {
        return new NewProductDataFactory();
    }

    @Bean
    public ManyToOneFactory<ProductData, ProductExistenceData, UpdateAvailabilityData> updateAvailabilityDataFactory() {
        return new UpdateAvailabilityDataFactory();
    }

    @Bean
    public Map<Class<?>, ManyToOneFactory<?, ?, ?>> factoryMap() {
        return Map.of(
                NewProductDataFactory.class, newProductDataFactory(),
                UpdateAvailabilityDataFactory.class, updateAvailabilityDataFactory()
        );
    }
}
