package com.itmo.techserv.controller;

import com.itmo.techserv.dto.ServiceRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Validated
@RequestMapping("api/techservice")
@RestController
public class TechServiceController {

    //Создание (регистрация) услуги
    @PostMapping
    public Long RegisterTechService(@Valid @RequestBody ServiceRequestDTO techServiceRequest0){
        return 0L;
    }
    //редактирование услуг(и), выбираемой по значению одного или нескольких полей
    @PutMapping
    public Long EditTechService(@Digits(integer = 1, fraction = Integer.MAX_VALUE) @RequestParam (required = false) int id,
                                @RequestParam (required = false) String type,
                                @RequestParam (required = false) String name ,
                                @RequestParam (required = false) String description,
                                @PastOrPresent @Future @RequestParam (required = false) LocalDate beginDate,
                                @PastOrPresent @Future @RequestParam (required = false) LocalDate finishDate){
        return 0L;
    }
    //Получение списка услуг
    //Получение услуги по идентификатору или значению любого поля
    @GetMapping
    public ServiceRequestDTO GetService(@Digits(integer = 1, fraction = Integer.MAX_VALUE)  @RequestParam int id,
                                        @RequestParam (required = false) String type,
                                        @RequestParam (required = false) String name,
                                        @RequestParam (required = false) String description,
                                        @PastOrPresent @Future @RequestParam (required = false) LocalDate beginDate,
                                        @PastOrPresent @Future @RequestParam (required = false) LocalDate finishDate){
        return null;
    }
}
