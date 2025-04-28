package com.itmo.techserv.repository;

import com.itmo.techserv.entity.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUserName(String userName);
    Optional<Users> findById(long id);
    boolean existsByUserName(String userName);
    boolean existsByUserNameAndPassword(String userName, String password);
    @Transactional
    @Query(nativeQuery = true, value = "SELECT 1 FROM tech.users mn" +
                                        "WHERE EXISTS (" +
                                        "SELECT 1 FROM tech.users us " +
                                        "WHERE us.user_name = :userName)")
    boolean existsByUserNameSQL(String userName);

}
