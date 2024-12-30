package com.itmo.techserv.mapper;

import com.itmo.techserv.dto.UserRequestDTO;
import com.itmo.techserv.entity.Roles;
import com.itmo.techserv.exceptions.ServiceException;
import com.itmo.techserv.repository.UserRoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import com.itmo.techserv.entity.Users;

@AllArgsConstructor
@Service
public class UserMapper {
    private final UserRoleRepository userRoleRepository;
    public Users MapToEntity(UserRequestDTO userRequestDTO,String userType){
        Users user = Users.builder()
                .userName(userRequestDTO.userName())
                .password(userRequestDTO.password())
                .build();
        user.setDiscount(0);
        Roles userRole = userRoleRepository.findByUserType(userType)
                         .orElseThrow(()->new ServiceException(HttpStatus.NOT_FOUND,"Такой роли пользователя не существует"));
        user.setUserRole(userRole);
        return user;
    }
}
