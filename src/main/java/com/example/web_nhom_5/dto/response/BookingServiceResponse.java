package com.example.web_nhom_5.dto.response;

import lombok.Data;

@Data
public class BookingServiceResponse {
    private long id;

    private String fullName;

    private String serviceName;

    private long totalPrice;

    private boolean isPaid;

    private String status;
}
