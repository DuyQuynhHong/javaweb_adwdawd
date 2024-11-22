package com.example.web_nhom_5.dto.response;

import com.example.web_nhom_5.entity.LocationEntity;
import lombok.Data;

@Data
public class RoomResponse {
    private long id;

    private String roomName;

    private String hotelName;

    private long roomPrice;

    private String roomDetail;

    private String roomType;

    private String locationName;
}
