package com.example.web_nhom_5.service.implement;

import com.example.web_nhom_5.conventer.ServiceMapper;
import com.example.web_nhom_5.dto.request.ServiceCreateRequestDTO;
import com.example.web_nhom_5.dto.request.ServiceUpdateRequestDTO;
import com.example.web_nhom_5.dto.response.ServiceResponse;
import com.example.web_nhom_5.entity.ServiceEntity;
import com.example.web_nhom_5.exception.ErrorCode;
import com.example.web_nhom_5.exception.WebException;
import com.example.web_nhom_5.repository.ServiceRepository;
import com.example.web_nhom_5.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.ServiceNotFoundException;
import java.util.List;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public ServiceEntity getServiceById(String id) {
        return serviceRepository.findById(id).orElseThrow(() -> new WebException(ErrorCode.SERVICE_NOT_FOUND));
    }

    @Override
    public ServiceResponse addService(ServiceCreateRequestDTO serviceCreateRequestDTO) {
        if (serviceRepository.existsById(serviceCreateRequestDTO.getCodeName())) {
            throw new WebException(ErrorCode.SERVICE_EXISTED);
        }
        ServiceEntity serviceEntity = serviceMapper.serviceCreateRequestToServiceEntity(serviceCreateRequestDTO);
        return serviceMapper.serviceEntityToServiceResponse(serviceRepository.save(serviceEntity));
    }

    @Override
    public ServiceResponse updateService(ServiceUpdateRequestDTO serviceUpdateRequestDTO, String id) {
        ServiceEntity serviceEntity = getServiceById(id);
        serviceMapper.updateService(serviceEntity, serviceUpdateRequestDTO);
        return serviceMapper.serviceEntityToServiceResponse(serviceRepository.save(serviceEntity));
    }

    @Override
    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll().stream().map(serviceMapper::serviceEntityToServiceResponse).toList();
    }

    @Override
    public void deleteService(String serviceId) {
        serviceRepository.deleteById(serviceId);
    }
}
