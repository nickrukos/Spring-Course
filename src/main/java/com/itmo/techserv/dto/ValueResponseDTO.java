package com.itmo.techserv.dto;

import java.time.LocalDate;

public record ValueResponseDTO(
        LocalDate date,     //Дата бронирования
        Long Value          //Стоимость услуги
) {
}
