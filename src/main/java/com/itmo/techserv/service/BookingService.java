package com.itmo.techserv.service;

import com.itmo.techserv.client.NotifierClient;
import com.itmo.techserv.dto.*;
import com.itmo.techserv.entity.Booking;
import com.itmo.techserv.entity.TechService;
import com.itmo.techserv.entity.Users;
import com.itmo.techserv.exceptions.AccountException;
import com.itmo.techserv.exceptions.ServiceException;
import com.itmo.techserv.mapper.BookingMapper;
import com.itmo.techserv.mapper.BookingStatMapper;
import com.itmo.techserv.mapper.TechServiceMapper;
import com.itmo.techserv.mapper.UserMapper;
import com.itmo.techserv.repository.BookingRepository;
import com.itmo.techserv.repository.ServiceRepository;
import com.itmo.techserv.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookingService {
    private final ServiceRepository serviceRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final TechServiceMapper techServiceMapper;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final NotifierClient notifierClient;
    private final BookingStatMapper bookingStatMapper;

    public long RegisterBooking(BookingRequestDTO bookingRequest){
        TechService techService = serviceRepository.findByName(bookingRequest.nameServ())
                                  .orElseThrow(()->new ServiceException(HttpStatus.BAD_REQUEST, "Указанная услуга не существует"));
        Booking booking = bookingMapper.mapToEntity(bookingRequest);
        booking.setService(techService);
        bookingRepository.save(booking);
        return booking.getId();
    }
    public void CancelBooking(BookingRequestDTO bookingRequest){
        Booking booking = bookingRepository.findById(bookingRequest.id()).get();
        if(booking == null) throw new ServiceException(HttpStatus.NOT_FOUND, "Бронирование не существует");
        booking.setCancelSign(true);
    }
    public void CancelBookingByAdmin(BookingRequestDTO bookingRequest){
        Booking booking = bookingRepository.findById(bookingRequest.id()).get();
        if(booking == null) throw new ServiceException(HttpStatus.NOT_FOUND, "Бронирование не существует");
        booking.setCancelSign(true);
        Users user = userRepository.findById(bookingRequest.idUser()).get();
        user.setDiscount(user.getDiscount()+5);
        booking.setCancelSign(true);
        notifierClient.notifyCancel(userMapper.MapToDTO(user));
    }
    public long EditBooking(long id, LocalDate date){
        Booking booking = bookingRepository.findById(id).get();
        if(booking == null) throw new ServiceException(HttpStatus.NOT_FOUND, "Бронирование не существует");
        booking.setBookingDate(date);
        return booking.getId();
    }
    public long EditBookingByAdmin(long id, LocalDate date){
        Booking booking = bookingRepository.findById(id).get();
        if(booking == null) throw new ServiceException(HttpStatus.NOT_FOUND, "Бронирование не существует");
        booking.setBookingDate(date);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        UserResponseDTO user = userMapper.MapToDTO(booking.getUser());
        notifierClient.notifyEdit(user,booking.getBookingDate().format(format));
        return booking.getUser().getId();
    }
    public List<BookingResponseDTO> GetBookingsByUser(Users user)
    {
        List<Booking> list = bookingRepository.findAllByUser(user);
        if(list.isEmpty()) throw new ServiceException(HttpStatus.NOT_FOUND, "Бронирование не существует");
        return list.stream().map(bookingMapper::mapToDTO).toList();
    }
    public List<ServiceResponseDTO> GetServicesByUser(Users user){
        List<TechService> list = bookingRepository.findServiceByUser(user);
        if(list.isEmpty())  throw new ServiceException(HttpStatus.NOT_FOUND, "Данные не найдены");
        return list.stream().map(techServiceMapper::mapToDTO).toList();
    }
    public List<ValueResponseDTO> GetValueByDate(LocalDate beginDate, LocalDate endDate){
        List<ValueResponseDTO> list = bookingRepository.SelectValue(beginDate,endDate);
        if(list.isEmpty())  throw new ServiceException(HttpStatus.NOT_FOUND, "Данные не найдены");
        return list;
    }
    public long SetDiscountToUser(String login, Integer discount) {
        Users user = userRepository.findByUserName(login)
                .orElseThrow(()->new ServiceException(HttpStatus.NOT_FOUND,"Такого пользователя не существует"));
        user.setDiscount(discount);
        return user.getDiscount();
    }
    //представление информации об оказанных услугах за предыдущие сутки для DWH-хранилища
    public List<BookingStatResponseDTO> GetLastDayServices(){
        List<Booking> bookings = bookingRepository.findByBookingDate(LocalDate.now().minusDays(1));
        return bookings.stream().map(bookingStatMapper::mapToDTO).toList();
    }
}
