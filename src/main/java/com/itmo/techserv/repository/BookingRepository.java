package com.itmo.techserv.repository;

import com.itmo.techserv.dto.ValueResponseDTO;
import com.itmo.techserv.entity.Booking;
import com.itmo.techserv.entity.TechService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
    //получение списка броней
    List<Booking> findAllByLogin(String login);

    //получение списка представленных услуг
    @Query(nativeQuery = true, value = "SELECT techservice.* FROM booking INNER JOIN techservice"
            + "ON techservice.id = booking.category_id "
            + "WHERE booking.login = :login")
    List<TechService> findServices(String login);

    //получение списка представленных услуг
    List<TechService> findServiceByLogin(String login);

    //создание брони на услугу сервиса
    @Transactional
    Booking save(Booking booking);

    //редактирование брони (изменение времени записи)
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE booking SET booking_date = :date"
            + "WHERE id = :id")
    Booking UpdateBookingDate(LocalDate date, long id);

    //редактирование брони (изменение времени записи)


//    @Transactional
//    @Modifying
//    Booking updateByBookingDate(LocalDate date, long id);

    //отмена брони
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE booking SET cancel_sign = true"
            + "WHERE id = :id")
    Booking UpdateBookingCancelSign(long id);


    //отмена брони
//    @Transactional
//    @Modifying
//    //Booking updateByIdAndCancelSignIsTrue(long id);
//    Booking  updateBookingById(Long id);

    //получение информации о выручке
    @Query(nativeQuery = true, value =  "SELECT booking.booking_date, techservice.value " +
                                        "FROM booking INNER JOIN techservice " +
                                        "ON techservice.id = booking.category_id " +
                                        "WHERE booking.booking_date BETWEEN :beginDate AND :endDate " +
                                        "AND booking.cansel_sign IS NOT true")
    List<ValueResponseDTO> SelectValue(LocalDate beginDate, LocalDate endDate);
}