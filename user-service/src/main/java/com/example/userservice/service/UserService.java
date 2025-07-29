package com.example.userservice.service;

import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.dto.UserResponseDto;
import com.example.userservice.entity.User;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.talonone.TalonOneClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TalonOneClient talonOneClient;

    @Transactional
    public UserResponseDto registerUser(UserRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .totalOrders(0)
                .totalSpent(0.0)
                .build();
        user = userRepository.save(user);
        talonOneClient.registerUserWithTalonOne(user.getId().toString(), user.getName(), user.getEmail(), user.getPhone());
        return toDto(user);
    }

    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return toDto(user);
    }

    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return toDto(user);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user = userRepository.save(user);
        // Optionally update Talon.One profile here
        talonOneClient.registerUserWithTalonOne(user.getId().toString(), user.getName(), user.getEmail(), user.getPhone());
        return toDto(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .totalOrders(user.getTotalOrders())
                .totalSpent(user.getTotalSpent())
                .build();
    }
}
