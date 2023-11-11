package com.honeybadgersoftware.productworker.api.availabilityService.client;

import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "availabilityService", url = "http://localhost:8082/availability")
public interface AvailabilityServiceApi {

    @PutMapping
    void updateAvailability(@RequestBody UpdateAvailabilityRequest updateAvailabilityRequest);
}
