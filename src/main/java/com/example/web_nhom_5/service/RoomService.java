package com.example.web_nhom_5.service;

import com.example.web_nhom_5.dto.request.RoomCreateRequestDTO;
import com.example.web_nhom_5.dto.request.RoomUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.RoomResponse;
import com.example.web_nhom_5.entity.RoomEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoomService {
    RoomResponse addRoom(RoomCreateRequestDTO room);
    RoomEntity getRoomById(Long roomId);
    List<RoomResponse> getAllRooms();
    RoomResponse updateRoom(RoomUpdateRequestDTO room, Long roomId);
    void deleteRoom(Long roomId);
    List<RoomResponse> getAllRoomsByLocationCode(String locationCode);

    List<RoomResponse> getLimitedRooms();
    // can them cac ham chuc nang tim kiem,loc theo yeu cau.
}
