package com.honeybadgersoftware.productworker.api.availabilityService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class UpdateAvailabilityData {

    private Long id;
    private BigDecimal price;
}
