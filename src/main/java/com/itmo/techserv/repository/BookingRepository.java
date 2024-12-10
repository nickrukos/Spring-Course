package com.itmo.techserv.repository;

import com.itmo.techserv.entity.Booking;
import com.itmo.techserv.entity.TechService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    void Save(Booking booking);

    //редактирование брони
    //Booking updateBookingBy
}
