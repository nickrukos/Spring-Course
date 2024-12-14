package com.itmo.techserv.service;

import com.itmo.techserv.constants.TechServiceType;
import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.dto.ServiceResponseDTO;
import com.itmo.techserv.entity.TechService;
import com.itmo.techserv.mapper.TechServiceMapper;
import com.itmo.techserv.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class TechAdminService {
    private final ServiceRepository serviceRepository;
    private final TechServiceMapper techServiceMapper;

    public List<ServiceResponseDTO> GetAllServices(){
        List<TechService> techServices = serviceRepository.findAll();
        return techServices.stream().map(techServiceMapper::mapToDTO).toList();
    }
    public ServiceResponseDTO GetServicesById(long id){
        TechService techService = serviceRepository.findById(id).get();
        return techServiceMapper.mapToDTO(techService);
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
        TechService techService = techServiceMapper.mapToEntity(serviceRequestDTO);
        serviceRepository.save(techService);
        return techService.getId();
    }
}
