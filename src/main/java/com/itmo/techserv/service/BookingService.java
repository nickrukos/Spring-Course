package com.itmo.techserv.service;

import com.itmo.techserv.dto.BookingRequestDTO;
import com.itmo.techserv.dto.BookingResponseDTO;
import com.itmo.techserv.dto.ServiceResponseDTO;
import com.itmo.techserv.dto.ValueResponseDTO;
import com.itmo.techserv.entity.Booking;
import com.itmo.techserv.entity.TechService;
import com.itmo.techserv.mapper.BookingMapper;
import com.itmo.techserv.mapper.TechServiceMapper;
import com.itmo.techserv.repository.BookingRepository;
import com.itmo.techserv.repository.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.ListQueryByExampleExecutor;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookingService {
    private final ServiceRepository serviceRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final TechServiceMapper techServiceMapper;

    public long RegisterBooking(BookingRequestDTO bookingRequest){
        Booking booking = bookingMapper.mapToEntity(bookingRequest);
        bookingRepository.save(booking);
        return booking.getId();
    }
    public void CancelBooking(BookingRequestDTO bookingRequest){
        Booking booking = bookingRepository.findById(bookingRequest.id()).get();
        booking.setCancelSign(true);
    }
    public long EditBooking(long id, LocalDate date){
        Booking booking = bookingRepository.findById(id).get();
        booking.setBookingDate(date);
        return booking.getId();
    }
    public List<BookingResponseDTO> GetBookingsByLogin(String login)
    {
        return bookingRepository.findAllByLogin(login).stream().map(bookingMapper::mapToDTO).toList();
    }
    public List<ServiceResponseDTO> GetServicesByLogin(String login){

        return bookingRepository.findServiceByLogin(login).stream().map(techServiceMapper::mapToDTO).toList();
    }
    public List<ValueResponseDTO> GetValuesByDate(LocalDate beginDate, LocalDate endDate){
        return bookingRepository.SelectValue(beginDate,endDate);
    }
}
