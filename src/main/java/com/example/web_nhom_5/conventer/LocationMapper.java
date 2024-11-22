package com.example.web_nhom_5.conventer;

import com.example.web_nhom_5.dto.request.LocationUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.LocationResponse;
import com.example.web_nhom_5.entity.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LocationMapper {
    LocationResponse locationEntityToLocationResponse(LocationEntity locationEntity);
    void updateLocation(@MappingTarget LocationEntity locationEntity, LocationUpdateRequestDTO locationUpdateRequestDTO);
}
