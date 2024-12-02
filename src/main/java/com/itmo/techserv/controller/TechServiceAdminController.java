package com.itmo.techserv.controller;

import com.itmo.techserv.constants.TechServiceType;
import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.dto.ServiceResponseDTO;
import com.itmo.techserv.service.TechAdminService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Long RegisterTechService(@Valid @RequestBody ServiceRequestDTO techServiceRequestDTO){
        return 0L;
    }
    //редактирование услуг(и), выбираемой по значению одного или нескольких полей
    @PutMapping
    public Long EditTechService(@NotNull @Digits(integer = 1, fraction = Integer.MAX_VALUE) @RequestParam (required = false) int id,
                                @NotNull @RequestParam (required = false) TechServiceType type,
                                @NotNull @RequestParam (required = false) String name,
                                @NotNull @RequestParam (required = false) String description,
                                @NotNull @RequestParam (required = false) int duration){
        return 0L;
    }
    //Получение всего перечня услуг
    //Получение услуги по идентификатору или значению любого поля
    @GetMapping
    public List<ServiceResponseDTO> GetListServices(@NotNull @Digits(integer = 1, fraction = Integer.MAX_VALUE) @RequestParam (required = false) int id,
                                                    @NotNull @RequestParam (required = false) TechServiceType type,
                                                    @NotNull @RequestParam (required = false) String name,
                                                    @NotNull @RequestParam (required = false) String description,
                                                    @NotNull @RequestParam (required = false) int duration){
        return null;
    }
}
