package com.honeybadgersoftware.productworker.api.productservice.model.request;

import com.honeybadgersoftware.productworker.api.productservice.model.data.NewProductUpdateData;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateNewProductsRequest {

    List<NewProductUpdateData> data;
}
