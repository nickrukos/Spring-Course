package com.itmo.techserv.repository;

import com.itmo.techserv.constants.TechServiceType;
import com.itmo.techserv.entity.TechService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository  extends JpaRepository<TechService,Long> {
    //создание услуги
    @Transactional
    TechService save(TechService service);

    //редактирование услуги по всем полям
//    @Transactional
//    @Modifying
//    TechService updateAllById(long id, TechServiceType type, String name,
//                              String description, int duration);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE techservice SET type = :type, name = :name," +
                                        "description = :description, duration = :duration" +
                                        "WHERE id = :id")
    TechService UpdateAllFields(long id, TechServiceType type, String name,
                                String description, int duration);

    //получение всего списка услуг
    @Transactional
    @Query(nativeQuery = true, value = "SELECT * FROM techservice")
    List<TechService> SelectAll();

    @Transactional
    List<TechService>  findAll();

    //получение услуги по идентификатору
    @Transactional
    TechService findById(long id);
}
