package dev.dwidi.transactionservice.client;

import dev.dwidi.transactionservice.dto.PublicResponseDTO;
import dev.dwidi.transactionservice.entity.dto.UserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/user/{id}")
    PublicResponseDTO<UserResponseDTO> getUserById(@PathVariable("id") Long id);
}
