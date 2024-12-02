package com.itmo.techserv.controller;

import com.itmo.techserv.constants.TechServiceType;
import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.dto.ServiceResponseDTO;
import com.itmo.techserv.service.TechAdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@Validated
@RequestMapping("api/techservice")
@RestController
public class TechServiceAdminController {
    private final TechAdminService techAdminService;

    @Autowired
    public TechServiceAdminController(TechAdminService techAdminService) {
        this.techAdminService = techAdminService;
    }

    //Создание услуги
    @PostMapping
    public  ResponseEntity<?> RegisterService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO,
                                              HttpServletRequest request){
        URI uri = URI.create("api/techservice"+ techAdminService.RegisterService(serviceRequestDTO));
        return ResponseEntity.created(uri).build();
    }
    //редактирование услуг(и), выбираемой по значению одного или нескольких полей
    @PutMapping
    public ResponseEntity<?> EditService(@NotNull @Min(1) @RequestParam int id,
                                         @NotNull @RequestParam TechServiceType type,
                                         @NotNull @RequestParam String name,
                                         @NotNull @RequestParam String description,
                                         @NotNull @RequestParam int duration,
                                         HttpServletRequest request){
        ServiceResponseDTO serviceResponseDTO = techAdminService.GetServicesById(id);
        URI uri = URI.create("api/techservice" + techAdminService.EditService(type,name,description, duration, serviceResponseDTO));
        return ResponseEntity.created(uri).build();
    }
    //Получение всего перечня услуг
    @GetMapping(produces = "application/json")
    public  List<ServiceResponseDTO> GetAllServices(){
        return techAdminService.GetAllServices();
    }
    //Получение услуги по идентификатору
    @GetMapping
    public ResponseEntity<ServiceResponseDTO> GetServicesById(@NotNull @Min(1) @RequestParam int id){
        return new ResponseEntity<>(techAdminService.GetServicesById(id), HttpStatus.OK);
    }
}
