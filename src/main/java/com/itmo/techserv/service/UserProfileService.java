package com.itmo.techserv.service;

import com.itmo.techserv.dto.UserRequestDTO;
import com.itmo.techserv.entity.Users;
import com.itmo.techserv.mapper.UserMapper;
import com.itmo.techserv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserProfileService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public long RegisterUser(UserRequestDTO userRequestDTO, String userType){
        Users user = userMapper.MapToEntity(userRequestDTO, userType);
        userRepository.save(user);
        return userRepository.findByUserName(user.getUserName()).get().getId();
    }
}
