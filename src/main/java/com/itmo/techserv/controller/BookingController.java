package com.itmo.techserv.controller;

import com.itmo.techserv.constants.UserType;
import com.itmo.techserv.dto.*;
import com.itmo.techserv.entity.Users;
import com.itmo.techserv.exceptions.ServiceException;
import com.itmo.techserv.repository.UserRepository;
import com.itmo.techserv.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/api/booking")
@RestController
public class BookingController {
    private final BookingService bookingService;
    private final UserRepository userRepository;

    //бронирование услуги (регистрация брони)
    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity<?> RegisterBooking(@Valid @RequestBody BookingRequestDTO booking,
                                             HttpServletRequest request){

        URI uri = URI.create("/api/booking/register?id="+ bookingService.RegisterBooking(booking));
        return ResponseEntity.created(uri).build();
    }
    //отмена бронирования пользователем
    @PutMapping(path = "/cancel", produces = "application/json")
    public ResponseEntity<Void> CancelBooking(@Valid @RequestBody BookingRequestDTO booking,
                              HttpServletRequest request){
        String userName = request.getUserPrincipal().getName();
        Users user = userRepository.findByUserName(userName).orElseThrow(
                ()->new ServiceException(HttpStatus.NOT_FOUND,"Некорректный пользователь"));
        if(user.getUserRole().getUserType() == UserType.ROLE_USER)
            bookingService.CancelBooking(booking);
        else bookingService.CancelBookingByAdmin(booking);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }
    //редактирование брони (изменение времени записи) пользователем
    @PutMapping(path = "/edit", produces = "application/json")
    public ResponseEntity<?> EditBooking(@NotNull @Min(1) @RequestParam Long id,
                                         @NotNull @Future @RequestParam  LocalDate date){
        URI uri = URI.create("/api/booking/edit?id=" + bookingService.EditBooking(id,date));
        return ResponseEntity.created(uri).build();
    }
    //редактирование брони (изменение времени записи) администратором или оператором
    @PutMapping(path = "/edit/admin", produces = "application/json")
    public ResponseEntity<?> EditBookingByAdmin(@NotNull @Min(1) @RequestParam Long id,
                                         @NotNull @Future @RequestParam  LocalDate date){
        long idUser = bookingService.EditBookingByAdmin(id,date);
        //URI uri = URI.create("/notifier/edit?booking="+id.toString()+"?date="+date.
        return null;
    }

    //получение списка броней
    @GetMapping(path = "/booking-list", produces = "application/json")
    public List<BookingResponseDTO> getBookingsByLogin(Principal principal){
        return bookingService.GetBookingsByLogin(principal.getName());
    }
    //получение списка предоставленных услуг
    @GetMapping(path = "/services-list", produces = "application/json")
    public List<ServiceResponseDTO> GetListServices(Principal principal){
        return bookingService.GetServicesByLogin(principal.getName());
    }
    //получение сведений о выручке за временной период
    @GetMapping(path = "/value",produces = "application/json")
    public List<ValueResponseDTO> GetValue(@NotNull @PastOrPresent @RequestParam LocalDate beginDate,
                                           @NotNull @PastOrPresent @RequestParam LocalDate endDate){
        return bookingService.GetValueByDate(beginDate,endDate);
    }
    //назначение скидки на бронь для пользователя
    @PutMapping(path = "/discount", produces = "application/json")
    public ResponseEntity<?> AssignDiscount(@NotNull @RequestParam String login,
                                            @NotNull @Min(0)  @RequestParam Integer discount){
        URI uri = URI.create("/api/booking/discount?id="+ bookingService.SetDiscountToUser(login,discount));
        return ResponseEntity.created(uri).build();
    }
}
