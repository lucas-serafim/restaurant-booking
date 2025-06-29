package com.serafim.restaurant_booking.model.service;

import com.serafim.restaurant_booking.model.domain.User.User;
import com.serafim.restaurant_booking.model.domain.User.UserRequestDTO;
import com.serafim.restaurant_booking.model.domain.User.UserResponseDTO;
import com.serafim.restaurant_booking.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO register(UserRequestDTO dto) {
        if (this.userRepository.findByEmail(dto.email()) != null) {
            return null;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());

        User user = new User(
                dto.name(),
                dto.email(),
                encryptedPassword,
                dto.role()
        );

        this.userRepository.save(user);

        return this.toResponseDTO(user);
    }

    private UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}
