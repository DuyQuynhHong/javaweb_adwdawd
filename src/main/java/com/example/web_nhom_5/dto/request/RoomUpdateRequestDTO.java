package com.example.web_nhom_5.dto.request;

import com.example.web_nhom_5.entity.LocationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomUpdateRequestDTO {

    private String roomName;

    private String hotelName;

    private long roomPrice;

    private String roomDetail;

    private String roomType;

    private String locationCode;
}
