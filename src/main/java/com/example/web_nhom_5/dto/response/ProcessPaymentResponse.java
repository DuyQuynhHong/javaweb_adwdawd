package com.example.web_nhom_5.dto.response;

import com.example.web_nhom_5.enums.BookingStatus;
import lombok.Data;

@Data
public class ProcessPaymentResponse {
    private long bookingId;
    private String userName;
    private boolean success;
    private long amount;
    private long cashBack;
}
