package com.example.web_nhom_5.conventer;

import com.example.web_nhom_5.dto.request.RoomCreateRequestDTO;
import com.example.web_nhom_5.dto.request.RoomUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.RoomResponse;
import com.example.web_nhom_5.entity.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoomMapper {
    // neu ten field cua nguon khac ten cua dich
    //@Mapping(source = "username", target = "name")
    //@Mapping(source = "emailAddress", target = "email")
    //source: Tên trường trong lớp nguồn.
    //target: Tên trường trong lớp đích.

    // neu khong muon map 1 field nao do
    //@Mapping(target = "roomName", ignore = true)

    RoomEntity roomCreateDtoToEntity(RoomCreateRequestDTO roomCreateRequestDTO);

    @Mapping(source = "location.locationName", target = "locationName")
    RoomResponse roomEntityToRoomResponse(RoomEntity roomEntity);

    @Mapping(target = "id", ignore = true)
    void updateRoom(@MappingTarget RoomEntity roomEntity, RoomUpdateRequestDTO roomUpdateRequestDTO);
}
