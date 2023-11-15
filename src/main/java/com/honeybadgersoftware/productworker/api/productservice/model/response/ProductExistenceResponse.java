package com.honeybadgersoftware.productworker.api.productservice.model.response;

import com.honeybadgersoftware.productworker.api.productservice.model.data.ProductExistenceData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductExistenceResponse {

    private List<ProductExistenceData> data;
}
