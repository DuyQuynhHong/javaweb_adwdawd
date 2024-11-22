package com.example.web_nhom_5.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookingServiceCreateRequestDTO {
    @NotNull
    private String serviceCodeName;

}
