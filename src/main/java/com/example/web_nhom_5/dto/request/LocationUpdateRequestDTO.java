package com.example.web_nhom_5.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationUpdateRequestDTO {
    @NotNull
    private String locationCode;

    private String locationName;
}
