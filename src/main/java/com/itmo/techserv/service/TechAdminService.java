package com.itmo.techserv.service;

import com.itmo.techserv.constants.TechServiceType;
import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.dto.ServiceResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class TechAdminService {
    public List<ServiceResponseDTO> GetAllServices(){
        return null;
    }
    public ServiceResponseDTO GetServicesById(long id){
        return null;
    }
    public long EditService(
            TechServiceType type,
            String name,
            String description,
            int duration,
            ServiceResponseDTO serviceResponseDTO){
        return 0L;
    }
    public long RegisterService(ServiceRequestDTO serviceRequestDTO){
        return 0L;
    }
}
