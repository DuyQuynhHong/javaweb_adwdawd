package com.example.web_nhom_5.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceCreateRequestDTO {

    @NotNull(message = "codeName cannot be null")
    private String codeName;

    @NotNull(message = "serviceName cannot be null")
    private String serviceName;

    @NotNull(message = "servicePrice cannot be null")
    private long servicePrice;

    @NotNull(message = "serviceDetail cannot be null")
    private String serviceDetail;

}
