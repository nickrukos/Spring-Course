package com.itmo.techserv.mapper;

import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.dto.ServiceResponseDTO;
import com.itmo.techserv.entity.TechService;
import org.springframework.stereotype.Service;

@Service
public class TechServiceMapper {
    public TechService mapToEntity(ServiceRequestDTO serviceRequestDTO){
        return TechService.builder()
                .id(serviceRequestDTO.id())
                .type(serviceRequestDTO.type())
                .name(serviceRequestDTO.name())
                .description(serviceRequestDTO.description())
                .duration(serviceRequestDTO.duration())
                .build();
    }
    public ServiceResponseDTO mapToDTO(TechService techService){
        return new ServiceResponseDTO(
                techService.getId(),
                techService.getType(),
                techService.getName(),
                techService.getDescription(),
                techService.getDuration()
        );
    }
}
