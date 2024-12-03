package com.itmo.techserv.service;

import com.itmo.techserv.dto.BookingRequestDTO;
import com.itmo.techserv.dto.BookingResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    public long RegisterBooking(BookingRequestDTO booking){
        return 0L;
    }
    public void CancelBooking(BookingRequestDTO booking){
        //тело метода
    }
    public long EditBooking(long id, LocalDate date, String login){
        return 0L;
    }
    public List<BookingResponseDTO> GetBookingsByLogin(String login){
        return null;
    }
}
