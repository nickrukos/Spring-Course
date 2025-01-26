package com.itmo.techserv.mapper;

import com.itmo.techserv.dto.BookingStatResponseDTO;
import com.itmo.techserv.entity.Booking;
import com.itmo.techserv.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookingStatMapper {
    private final ServiceRepository serviceRepository;
    public BookingStatResponseDTO mapToDTO(Booking booking){
        return new BookingStatResponseDTO(
                booking.getId(),
                booking.getService().getId(),
                booking.getUser().getId(),
                serviceRepository.findById(booking.getService().getId()).get().getValue()
                      * booking.getUser().getDiscount()*100
        );
    }
}
