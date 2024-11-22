package com.example.web_nhom_5.dto.request;

import com.example.web_nhom_5.entity.LocationEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomCreateRequestDTO {
    @NotNull
    private String roomName;

    @NotNull
    private String hotelName;

    @NotNull
    private long roomPrice;

    @NotNull
    private String roomDetail;

    @NotNull
    private String roomType;

    @NotNull
    private String locationCode;
}
