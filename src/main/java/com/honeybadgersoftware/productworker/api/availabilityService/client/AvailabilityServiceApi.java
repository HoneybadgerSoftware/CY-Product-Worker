package com.honeybadgersoftware.productworker.api.availabilityService.client;

import com.honeybadgersoftware.productworker.api.availabilityService.model.UpdateAvailabilityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "availabilityService", url = "http://localhost:8081/availability")
public interface AvailabilityServiceApi {

    @PutMapping("/update")
    ResponseEntity<Void> updateAvailability(@RequestBody UpdateAvailabilityRequest updateAvailabilityRequest);
}
