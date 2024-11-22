package com.example.web_nhom_5.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceUpdateRequestDTO {

    private String serviceName;

    private long servicePrice;

    private String serviceDetail;

}
