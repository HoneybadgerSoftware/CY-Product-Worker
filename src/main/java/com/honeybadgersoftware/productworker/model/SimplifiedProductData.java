package com.honeybadgersoftware.productworker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimplifiedProductData {
    private String productName;
    private String manufacturer;
}
