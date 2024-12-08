package com.itmo.techserv.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record BookingRequestDTO (
        @NotNull
        @Min(1)
        Long number,               //номер услуги
        @NotNull
        String clientLogin,          //логин пользователя
        @NotNull
        String clientPhone,  //телефон пользователя
        @NotNull
        ServiceRequestDTO service, //содержание услуги
        @NotNull
        @PastOrPresent
        LocalDate serviceDate,      //дата обращения за услугой
        @NotNull
        boolean cancelSign          //отметка об аннулировании брони
){

}



