package com.itmo.techserv.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotNull
        String userName,
        @NotNull
        String password
) {
}
