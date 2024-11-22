package com.example.web_nhom_5.conventer;

import com.example.web_nhom_5.dto.request.BookingRoomCreateRequestDTO;
import com.example.web_nhom_5.dto.response.BookingRoomResponse;
import com.example.web_nhom_5.entity.BookingRoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookingRoomMapper {

    @Mapping(target = "status", ignore = true)
    BookingRoomEntity bookingRoomCreateRequestToBookingRoomEntity(BookingRoomCreateRequestDTO bookingRoomCreateRequestDTO);

    @Mapping(source = "user.fullName", target = "fullName")
    @Mapping(source = "room.roomName", target = "roomName")
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "user.id", target = "userId")
    BookingRoomResponse bookingRoomEntityToBookingRoomResponse(BookingRoomEntity bookingRoomEntity);
}
