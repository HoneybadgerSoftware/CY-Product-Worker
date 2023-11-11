package com.honeybadgersoftware.productworker.api.productservice.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductExistenceData {
    private Long id;
    private boolean existsInDb;
    private String name;
    private String manufacturer;
}
