package com.belenits.usermanagementapi.service;

import com.belenits.usermanagementapi.dto.PassResetDTO;
import com.belenits.usermanagementapi.dto.UserRegistrationDTO;

public interface UserService {
    public UserRegistrationDTO registerUser(UserRegistrationDTO userRegistrationDTO);

    String login(String email, String password);

    String resetPassword(PassResetDTO passResetDTO);
}
