package com.itmo.techserv.client;

import com.itmo.techserv.dto.UserRequestDTO;
import com.itmo.techserv.dto.UserResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "notifier", url = "http:/localhost:8086/api/notifier")
public interface NotifierClient {
    @PostMapping("/cancel")
    ResponseEntity<Void> notifyCancel(@Valid @RequestBody UserResponseDTO user);
    @PostMapping("/edit")
    ResponseEntity<Void> notifyEdit(@Valid @RequestBody UserResponseDTO user,
                                    @NotNull @RequestParam String date);


}
