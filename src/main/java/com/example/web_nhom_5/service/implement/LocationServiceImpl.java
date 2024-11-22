package com.example.web_nhom_5.service.implement;

import com.example.web_nhom_5.conventer.LocationMapper;
import com.example.web_nhom_5.dto.request.LocationUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.LocationResponse;
import com.example.web_nhom_5.entity.LocationEntity;
import com.example.web_nhom_5.entity.RoomEntity;
import com.example.web_nhom_5.exception.ErrorCode;
import com.example.web_nhom_5.exception.WebException;
import com.example.web_nhom_5.repository.LocationRepository;
import com.example.web_nhom_5.service.LocationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    @PostConstruct
    public void init() {
        createDefaultLocations();  // ham khoi tao gia tri mac dinh cua cac location.
    }

    private void createDefaultLocations() {
        createLocationIfNotExists("HCM", "TP Hồ Chí Minh");
        createLocationIfNotExists("DA-NANG", "Đà Nẵng");
        createLocationIfNotExists("HA-NOI", "Hà Nội");
        createLocationIfNotExists("PHU-QUOC", "Phú Quốc");
        createLocationIfNotExists("HOI-AN", "Hội An");
    }

    private void createLocationIfNotExists(String code, String name) {
        if (!locationRepository.existsById(code)) {
            LocationEntity location = new LocationEntity();
            location.setLocationCode(code);
            location.setLocationName(name);
            List<RoomEntity> room = new ArrayList<>();
            location.setRooms(room);
            locationRepository.save(location);
        }
    }

    @Override
    public List<LocationResponse> getAllLocation() {
        return locationRepository.findAll().stream().map(locationMapper::locationEntityToLocationResponse).toList();
    }

    @Override
    public LocationResponse getLocationById(String locationId) {
        LocationEntity locationEntity = locationRepository.findById(locationId)
                .orElseThrow(() -> new WebException(ErrorCode.LOCATION_NOT_FOUND));
        return locationMapper.locationEntityToLocationResponse(locationEntity);
    }


    @Override
    public LocationResponse updateLocation(LocationUpdateRequestDTO location, String locationId) {
        LocationEntity locationEntity = locationRepository.findById(locationId)
                .orElseThrow(() -> new WebException(ErrorCode.LOCATION_NOT_FOUND));
        locationMapper.updateLocation(locationEntity, location);
        LocationEntity updatedLocation = locationRepository.save(locationEntity);
        return locationMapper.locationEntityToLocationResponse(updatedLocation);
    }

    @Override
    public void deleteLocation(String locationId) {
        locationRepository.deleteById(locationId);
    }

}
