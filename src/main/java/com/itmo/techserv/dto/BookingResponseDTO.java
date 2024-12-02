package com.itmo.techserv.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record BookingResponseDTO (
        @NotNull
        @Min(1)
        Long number,               //номер услуги
        @NotNull
        String userLogin,          //логин пользователя
        @NotNull
        String userContactNumber,  //телефон пользователя
        ServiceRequestDTO service, //содержание услуги
        @NotNull
        @PastOrPresent
        LocalDate serviceDate,      //дата обращения за услугой
        @NotNull
        boolean cancelSign          //отметка об аннулировании брони
){

}