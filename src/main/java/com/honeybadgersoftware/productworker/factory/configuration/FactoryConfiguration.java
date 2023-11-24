package com.honeybadgersoftware.productworker.factory.configuration;

import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityData;
import com.honeybadgersoftware.productworker.api.productservice.model.data.NewProductUpdateData;
import com.honeybadgersoftware.productworker.factory.NewProductDataFactory;
import com.honeybadgersoftware.productworker.factory.UpdateAvailabilityDataFactory;
import com.honeybadgersoftware.productworker.utils.factory.ManyToOneFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class FactoryConfiguration {

    private final NewProductDataFactory newProductDataFactory;
    private final UpdateAvailabilityDataFactory updateAvailabilityDataFactory;

    @Bean
    @DependsOn(value = {"newProductDataFactory", "updateAvailabilityDataFactory"})
    public Map<Class<?>, ManyToOneFactory<?, ?, ?>> factoryMap() {
        System.out.println("Factory map");
        return Map.of(
                NewProductUpdateData.class, newProductDataFactory,
                UpdateAvailabilityData.class, updateAvailabilityDataFactory);
    }
}
