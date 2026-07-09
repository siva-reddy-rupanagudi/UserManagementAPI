package com.belenits.usermanagementapi.contoller;

import com.belenits.usermanagementapi.dto.PassResetDTO;
import com.belenits.usermanagementapi.dto.UserRegistrationDTO;
import com.belenits.usermanagementapi.response.ApiResponse;
import com.belenits.usermanagementapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserRegistrationDTO>> registerUser(UserRegistrationDTO userRegistrationDTO) {
        UserRegistrationDTO userRegistrationDTO1 = userService.registerUser(userRegistrationDTO);
        ApiResponse<UserRegistrationDTO> response = new ApiResponse<>();
        response.setData(userRegistrationDTO1);
        response.setStatus(201);
        response.setMessage("User registration successful,you need to login with mailed password");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/resetPassword")
    public ResponseEntity<ApiResponse<String>> resetPassword(@ModelAttribute PassResetDTO passResetDTO) {
        String token = userService.resetPassword(passResetDTO);
        ApiResponse<String> response = new ApiResponse<>();
        response.setData(token);
        response.setStatus(200);
        response.setMessage("Password reset successful");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestParam String email, @RequestParam String password) {
        logger.info("Received /login request for email={}", email);
        String token = userService.login(email, password);
        logger.info("Login handler finished for email={}", email);
        ApiResponse<String> response = new ApiResponse<>();
        response.setData(token);
        response.setStatus(200);
        response.setMessage("");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
