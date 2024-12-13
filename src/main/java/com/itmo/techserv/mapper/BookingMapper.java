package com.itmo.techserv.mapper;

import com.itmo.techserv.dto.BookingRequestDTO;
import com.itmo.techserv.dto.BookingResponseDTO;
import com.itmo.techserv.entity.Booking;
import com.itmo.techserv.entity.TechService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {
    private final TechServiceMapper techServiceMapper;
    @Autowired
    public BookingMapper(TechServiceMapper techServiceMapper) {
        this.techServiceMapper = techServiceMapper;
    }

    public Booking mapToEntity(BookingRequestDTO bookingRequestDTO){
        return  Booking.builder()
                .id(bookingRequestDTO.id())
                .login(bookingRequestDTO.login())
                .clientPhone(bookingRequestDTO.clientPhone())
                .service(techServiceMapper.mapToEntity(bookingRequestDTO.service()))
                .bookingDate(bookingRequestDTO.bookingDate())
                .cancelSign(bookingRequestDTO.cancelSign())
                .build();
    }
    public BookingResponseDTO mapToDTO(Booking booking){
       return new BookingResponseDTO(
               booking.getId(),
               booking.getLogin(),
               booking.getClientPhone(),
               booking.getService(),
               booking.getBookingDate(),
               booking.isCancelSign()
       );
    }
}