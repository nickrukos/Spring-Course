package com.itmo.techserv.client;

import com.itmo.techserv.dto.UserRequestDTO;
import com.itmo.techserv.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "notifier", url = "http:/localhost:8086/api/notifier")
public interface NotifierClient {
    @PostMapping("/cancel")
    ResponseEntity<Void> notifyCancel(@Valid @RequestBody UserResponseDTO user);
}
