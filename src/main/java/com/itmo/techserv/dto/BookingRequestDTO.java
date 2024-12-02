package com.itmo.techserv.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record BookingRequestDTO (
        @NotNull
        @Min(1)
        Long number,               //номер услуги
        @NotNull
        String userLogin,          //логин пользователя
        @NotNull
        String userContactNumber,  //телефон пользователя
        @NotNull
        ServiceRequestDTO service, //содержание услуги
        @NotNull
        @PastOrPresent
        LocalDate serviceDate,      //дата обращения за услугой
        @NotNull
        boolean cancelSign          //отметка об аннулировании брони
){

}



