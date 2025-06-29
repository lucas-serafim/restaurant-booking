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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
