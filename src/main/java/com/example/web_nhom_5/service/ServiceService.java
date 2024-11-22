package com.example.web_nhom_5.service;

import com.example.web_nhom_5.dto.request.ServiceCreateRequestDTO;
import com.example.web_nhom_5.dto.request.ServiceUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.ServiceResponse;
import com.example.web_nhom_5.entity.ServiceEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ServiceService {
    ServiceEntity getServiceById(String id);
    ServiceResponse addService(ServiceCreateRequestDTO serviceCreateRequestDTO);
    ServiceResponse updateService(ServiceUpdateRequestDTO serviceUpdateRequestDTO, String id);
    List<ServiceResponse> getAllServices();
    void deleteService(String serviceId);
    // can them cac ham chuc nang tim kiem, loc theo yeu cau.
}
