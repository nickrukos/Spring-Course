package com.itmo.techserv.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record BookingRequestDTO (
        @NotNull
        @Min(1)
        Long id,               //идентификатор услуги
        @NotNull
        long idUser,          //id пользователя
        @NotNull
        String nameServ,     //название услуги
        @NotNull
        @PastOrPresent
        LocalDate bookingDate,      //дата обращения за услугой
        @NotNull
        boolean cancelSign          //отметка об аннулировании брони
){

}



