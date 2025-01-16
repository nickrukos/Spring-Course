package com.itmo.techserv.repository;

import com.itmo.techserv.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUserName(String userName);
    Optional<Users> findById(long id);
    boolean existsByUserName(String userName);
    boolean existsByUserNameAndPassword(String userName, String password);
}
