package com.itmo.techserv.dto;

import com.itmo.techserv.constants.TechServiceType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ServiceRequestDTO (
        @NotNull
        @Digits(integer = 1, fraction = Integer.MAX_VALUE)
        int id,              //идентификатор услуги
        @NotNull
        TechServiceType type,            //тип услуги
        @NotNull
        String name,         //название услуги
        @NotNull
        String description,  //описание услуги
        @NotNull
        @PastOrPresent
        LocalDate beginDate, //дата начала оказания услуги
        @NotNull
        LocalDate finishDate //дата окончания оказания услуги
){
}
