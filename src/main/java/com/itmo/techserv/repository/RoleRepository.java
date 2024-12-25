package com.itmo.techserv.repository;

import com.itmo.techserv.constants.UserType;
import com.itmo.techserv.entity.Roles;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByUserType(UserType userType);
}
