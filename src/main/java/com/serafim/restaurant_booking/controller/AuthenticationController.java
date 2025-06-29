package com.serafim.restaurant_booking.controller;

import com.serafim.restaurant_booking.model.domain.User.UserRequestDTO;
import com.serafim.restaurant_booking.model.domain.User.UserResponseDTO;
import com.serafim.restaurant_booking.model.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/users")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO body) {
        UserResponseDTO response = authenticationService.register(body);

        if (response == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(response);
    }
}
