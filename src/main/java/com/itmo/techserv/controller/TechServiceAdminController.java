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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("api/techservice")
@RestController
public class TechServiceAdminController {
    private final TechAdminService techAdminService;

    //Создание услуги
    @PostMapping(path = "/register")
    public  ResponseEntity<?> RegisterService(@Valid @RequestBody ServiceRequestDTO serviceRequestDTO,
                                              HttpServletRequest request){
        URI uri = URI.create("api/techservices?id="+ techAdminService.RegisterService(serviceRequestDTO));
        return ResponseEntity.created(uri).build();
    }
    //редактирование услуг(и), выбираемой по значению поля id
    @PutMapping(path = "/edit/services}")
    public ResponseEntity<?> EditService(@NotNull @Min(1) @RequestParam long id,
                                         @NotNull @RequestParam TechServiceType type,
                                         @NotNull @RequestParam String name,
                                         @NotNull @RequestParam String description,
                                         @NotNull @RequestParam long value,
                                         @NotNull @RequestParam int duration,
                                         HttpServletRequest request){

        URI uri = URI.create("api/techservice?id=" + techAdminService.EditService(id,type,name,description, value, duration));
        return ResponseEntity.created(uri).build();
    }
    //Получение всего перечня услуг
    @GetMapping(path = "/list",  produces = "application/json")
    public  List<ServiceResponseDTO> GetAllServices(){
        return techAdminService.GetAllServices();
    }
    //Получение услуги по идентификатору
    @GetMapping(path="/service", produces = "application/json")
    public ResponseEntity<ServiceResponseDTO> GetServicesById(@NotNull @Min(1) @RequestParam long id){
        return new ResponseEntity<>(techAdminService.GetServicesById(id), HttpStatus.OK);
    }
}
