package com.itmo.techserv.service;

import com.itmo.techserv.constants.TechServiceType;
import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.dto.ServiceResponseDTO;
import com.itmo.techserv.entity.TechService;
import com.itmo.techserv.exceptions.ServiceException;
import com.itmo.techserv.mapper.TechServiceMapper;
import com.itmo.techserv.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class TechAdminService {
    private final ServiceRepository serviceRepository;
    private final TechServiceMapper techServiceMapper;

    public List<ServiceResponseDTO> GetAllServices(){
        List<TechService> list = serviceRepository.findAll();
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND, "Услуги не найдены");
        return list.stream().map(techServiceMapper::mapToDTO).toList();
    }
    public ServiceResponseDTO GetServicesById(long id){
        TechService techService = serviceRepository.findById(id).get();
        if(techService == null) throw new ServiceException(HttpStatus.NOT_FOUND, "Услуга не найдены");
        return techServiceMapper.mapToDTO(techService);
    }
    public long EditService(long id, TechServiceType type, String name, String description, long value, int duration){
        TechService techService = serviceRepository.UpdateAllFields(id,type,name,description,value,duration).get();
        if(techService == null) throw new ServiceException(HttpStatus.NOT_FOUND, "Услуга не найдены");
        return id;
    }
    public long RegisterService(ServiceRequestDTO serviceRequestDTO){
        TechService techService = techServiceMapper.mapToEntity(serviceRequestDTO);
        serviceRepository.save(techService);
        return techService.getId();
    }
}
