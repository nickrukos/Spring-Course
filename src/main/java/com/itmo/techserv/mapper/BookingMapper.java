package com.itmo.techserv.mapper;

import com.itmo.techserv.dto.BookingRequestDTO;
import com.itmo.techserv.dto.BookingResponseDTO;
import com.itmo.techserv.entity.Booking;
import com.itmo.techserv.entity.TechService;
import com.itmo.techserv.repository.ServiceRepository;
import com.itmo.techserv.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {
    private final TechServiceMapper techServiceMapper;
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;
    @Autowired
    public BookingMapper(TechServiceMapper techServiceMapper,
                         ServiceRepository serviceRepository,
                         UserRepository userRepository) {
        this.techServiceMapper = techServiceMapper;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public Booking mapToEntity(BookingRequestDTO bookingRequestDTO){
        return  Booking.builder()
                .id(bookingRequestDTO.id())
                .user(userRepository.findById(bookingRequestDTO.id()).get())
                .bookingDate(bookingRequestDTO.bookingDate())
                .cancelSign(bookingRequestDTO.cancelSign())
                .build();
    }
    public BookingResponseDTO mapToDTO(Booking booking){
       return new BookingResponseDTO(
               booking.getId(),
               booking.getUser().getId(),
               booking.getService().getName(),
               booking.getBookingDate(),
               booking.isCancelSign()
       );
    }
}
