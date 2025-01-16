package com.itmo.techserv.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record BookingResponseDTO (
        @NotNull
        @Min(1)
        Long id,               //идентификатор услуги
        @NotNull
        long idUser,          //id пользователя
        String nameServ,     //название услуги
        @NotNull
        @PastOrPresent
        LocalDate serviceDate,      //дата обращения за услугой
        @NotNull
        boolean cancelSign          //отметка об аннулировании брони
){

}