package com.example.web_nhom_5.service;

import com.example.web_nhom_5.dto.request.LocationUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.LocationResponse;
import com.example.web_nhom_5.entity.LocationEntity;

import java.util.List;

public interface LocationService {
    List<LocationResponse> getAllLocation();
    LocationResponse getLocationById(String locationId);
    LocationResponse updateLocation(LocationUpdateRequestDTO location, String locationId);
    void deleteLocation(String locationId);
}
