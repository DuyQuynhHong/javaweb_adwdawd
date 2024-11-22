package com.example.web_nhom_5.dto.response;

import com.example.web_nhom_5.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRoomResponse {
    private long id;

    private BookingStatus status;

    private boolean isPaid;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate checkIn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate checkOut;

    private int numOfPeople;

    private long totalPrice;

    private long roomId;

    private String roomName;

    private long userId;

    private String fullName;
}
