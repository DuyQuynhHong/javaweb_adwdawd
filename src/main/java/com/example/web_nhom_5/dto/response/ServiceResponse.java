package com.example.web_nhom_5.dto.response;

import lombok.Data;

@Data
public class ServiceResponse {
    private String codeName;

    private String serviceName;

    private long servicePrice;

    private String serviceDetail;
}
