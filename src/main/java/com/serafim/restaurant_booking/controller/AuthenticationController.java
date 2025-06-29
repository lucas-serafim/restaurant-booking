package com.serafim.restaurant_booking.controller;

import com.serafim.restaurant_booking.model.domain.authorization.AuthenticationDTO;
import com.serafim.restaurant_booking.model.domain.authorization.LoginResponseDTO;
import com.serafim.restaurant_booking.model.domain.user.UserRequestDTO;
import com.serafim.restaurant_booking.model.domain.user.UserResponseDTO;
import com.serafim.restaurant_booking.model.service.AuthenticationService;
import com.serafim.restaurant_booking.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/users")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO body) {
        UserResponseDTO response = userService.register(body);

        if (response == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO body) {
        LoginResponseDTO response = userService.login(body);
        return ResponseEntity.ok(response);
    }
}
