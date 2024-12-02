package com.itmo.techserv.controller;

import com.itmo.techserv.dto.BookingRequestDTO;
import com.itmo.techserv.dto.BookingResponseDTO;
import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.service.BookingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Validated
@RequestMapping("api/booking")
@RestController
public class BookingController {
    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    //бронирование услуги
    @PostMapping
    public long RegisterBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO){
        return 0L;
    }
    //редактирование и отмена брони
    @PutMapping
    public long EditBooking(@NotNull @RequestParam String userLogin,
                            @NotNull @Min(1) @RequestParam Long number){
        return 0L;
    }
    //получение списка броней
    //получение списка предоставленных услуг
    @GetMapping
    public List<BookingResponseDTO> GetListBooking(
            @NotNull @RequestParam String userLogin,
            @NotNull @Min(1) @RequestParam (required = false) Long number,
            @NotNull @RequestParam (required = false) String userContactNumber,
            @NotNull @PastOrPresent @RequestParam (required = false) LocalDate serviceDate,
            @NotNull @PastOrPresent @RequestParam (required = false) boolean cancelSign
    ){
        return null;
    }
}
