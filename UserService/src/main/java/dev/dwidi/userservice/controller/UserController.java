package dev.dwidi.userservice.controller;

import dev.dwidi.userservice.dto.PublicResponseDTO;
import dev.dwidi.userservice.dto.UserRequestDTO;
import dev.dwidi.userservice.dto.UserResponseDTO;
import dev.dwidi.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public PublicResponseDTO<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        log.info("Registering user with username: {} and email: {}", userRequestDTO.getUsername(), userRequestDTO.getEmail());
        return userService.registerUser(userRequestDTO);
    }

    @GetMapping("/all")
    public PublicResponseDTO<List<UserResponseDTO>> getAllUsers() {
        log.info("Fetching all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public PublicResponseDTO<UserResponseDTO> getUserById(@PathVariable Long id) {
        log.info("Fetching user with id: {}", id);
        return userService.getUserById(id);
    }
}
