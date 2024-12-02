package com.itmo.techserv.service;

import com.itmo.techserv.constants.TechServiceType;
import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.dto.ServiceResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechAdminService {
    public List<ServiceResponseDTO> GetAllServices(){
        return null;
    }
    public ServiceResponseDTO GetServicesById(int id){
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
