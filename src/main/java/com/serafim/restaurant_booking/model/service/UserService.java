package com.serafim.restaurant_booking.model.service;

import com.serafim.restaurant_booking.model.domain.authorization.AuthenticationDTO;
import com.serafim.restaurant_booking.model.domain.authorization.LoginResponseDTO;
import com.serafim.restaurant_booking.model.domain.user.User;
import com.serafim.restaurant_booking.model.domain.user.UserRequestDTO;
import com.serafim.restaurant_booking.model.domain.user.UserResponseDTO;
import com.serafim.restaurant_booking.model.infra.security.TokenService;
import com.serafim.restaurant_booking.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

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

    public LoginResponseDTO login(AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User) auth.getPrincipal());

        return new LoginResponseDTO(token);
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
