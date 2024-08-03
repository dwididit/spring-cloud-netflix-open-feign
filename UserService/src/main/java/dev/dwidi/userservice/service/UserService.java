package dev.dwidi.userservice.service;

import dev.dwidi.userservice.dto.PublicResponseDTO;
import dev.dwidi.userservice.dto.UserRequestDTO;
import dev.dwidi.userservice.dto.UserResponseDTO;
import dev.dwidi.userservice.entity.User;
import dev.dwidi.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public PublicResponseDTO<UserResponseDTO> registerUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername()))
            return new PublicResponseDTO<>(400, "Username is already taken", null);
        if (userRepository.existsByEmail(userRequestDTO.getEmail())) {
           return new PublicResponseDTO<>(400, "Email is already taken", null);
        }

        User user = new User();
        user.setUsername(userRequestDTO.getUsername());
        user.setEmail(userRequestDTO.getEmail());
        userRepository.save(user);

        return new PublicResponseDTO<>(200, "User registered successfully", new UserResponseDTO(user));

    }

    public PublicResponseDTO<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOs = users.stream().map(UserResponseDTO::new).collect(Collectors.toList());
        return new PublicResponseDTO<>(200, "Users fetched successfully", userResponseDTOs);
    }

    public PublicResponseDTO<UserResponseDTO> getUserById(Long id) {
        User user = userRepository.findByUserId(id).orElse(null);
        if (user == null) {
            return new PublicResponseDTO<>(404, "User not found", null);
        }
        return new PublicResponseDTO<>(200, "User fetched successfully", new UserResponseDTO(user));
    }

}
