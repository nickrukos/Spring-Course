package com.itmo.techserv.repository;

import com.itmo.techserv.entity.Roles;
import com.itmo.techserv.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByUserType(String type);
}
