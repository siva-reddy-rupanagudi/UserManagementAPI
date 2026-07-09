package com.belenits.usermanagementapi.service;

import com.belenits.usermanagementapi.dto.PassResetDTO;
import com.belenits.usermanagementapi.dto.UserRegistrationDTO;
import com.belenits.usermanagementapi.entity.UserRegistration;

import java.util.List;

public interface UserService {
    UserRegistrationDTO registerUser(UserRegistrationDTO userRegistrationDTO);

    String login(String email, String password);

    String resetPassword(PassResetDTO passResetDTO);

    List<UserRegistration> getAllUsers();

    UserRegistration getUserByEmail(String email);
}
