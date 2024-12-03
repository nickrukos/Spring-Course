package com.itmo.techserv.controller;

import com.itmo.techserv.dto.BookingRequestDTO;
import com.itmo.techserv.dto.BookingResponseDTO;
import com.itmo.techserv.dto.ServiceRequestDTO;
import com.itmo.techserv.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
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
    //бронирование услуги (регистрация брони)
    @PostMapping
    public ResponseEntity<?> RegisterBooking(@Valid @RequestBody BookingRequestDTO booking,
                                             HttpServletRequest request){

        URI uri = URI.create("api/booking"+ bookingService.RegisterBooking(booking));
        return ResponseEntity.created(uri).build();
    }
    //отмена бронирования
    @PutMapping(path = "/cancel")
    public ResponseEntity<Void> CancelBooking(@Valid @RequestBody BookingRequestDTO booking,
                              HttpServletRequest request){
        bookingService.CancelBooking(booking);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
    //редактирование брони (изменение времени записи)
    @PutMapping(path = "/edit", produces = "application/json")
    public ResponseEntity<?> EditBooking(@NotNull @Min(1) @RequestParam Long id,
                                         @NotNull @Future LocalDate date,
                                         Principal principal){
        URI uri = URI.create("api/booking/edit/" + bookingService.EditBooking(id,date,
                             principal.getName()));
        return ResponseEntity.created(uri).build();
    }
    //получение списка броней
    @GetMapping(path = "/booking-list", produces = "application/json")
    public List<BookingResponseDTO> getBookingsByLogin(Principal principal){
        return bookingService.GetBookingsByLogin(principal.getName());
    }
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
