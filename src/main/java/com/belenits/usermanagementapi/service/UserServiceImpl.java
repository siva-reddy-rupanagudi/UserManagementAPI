package com.belenits.usermanagementapi.service;

import com.belenits.usermanagementapi.dto.PassResetDTO;
import com.belenits.usermanagementapi.dto.UserRegistrationDTO;
import com.belenits.usermanagementapi.entity.UserRegistration;
import com.belenits.usermanagementapi.exception.LoginFailedException;
import com.belenits.usermanagementapi.exception.QuoteFetchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.belenits.usermanagementapi.repo.UserRegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRegistrationRepo userRegistrationRepo;
    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private void sendTemporaryPasswordEmail(String toEmail, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("rupanagudisivareddy@gmail.com");
        message.setTo(toEmail);
        message.setSubject("Your Temporary Password");

        message.setText("Your password has been reset. \n\n" +
                "Your temporary password is: " + tempPassword + "\n\n" +
                "Please log in and change this password immediately in your account settings.");
        mailSender.send(message);
    }

    private static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        String str = "ASDFGHJKLZXCVBNMQWERTYUIOPzxcvbnmasdfghjklqwertyuiop1234567890!@#$%^&*()";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(str.length());
            sb.append(str.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public UserRegistrationDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        String temPass = generateRandomPassword();
        UserRegistration userRegistration = UserRegistration.builder()
                .fullName(userRegistrationDTO.getFullName())
                .email(userRegistrationDTO.getEmail())
                .phone(userRegistrationDTO.getPhone())
                .country(userRegistrationDTO.getCountry())
                .state(userRegistrationDTO.getState())
                .city(userRegistrationDTO.getCity())
                .password(temPass)
                .active(false)
                .build();
        UserRegistration registration = userRegistrationRepo.save(userRegistration);
        sendTemporaryPasswordEmail(userRegistrationDTO.getEmail(), temPass);

        return UserRegistrationDTO.builder()
                .fullName(registration.getFullName())
                .email(registration.getEmail())
                .phone(registration.getPhone())
                .country(registration.getCountry())
                .state(registration.getState())
                .city(registration.getCity())
                .build();
    }

    @Override
    public String login(String email, String password) {
        logger.info("Login attempt for email={}", email);
        UserRegistration user = userRegistrationRepo.findByEmail(email);
        if (user == null) {
            logger.warn("Login failed: email not found: {}", email);
            throw new LoginFailedException("Email does not exist");
        } else if (!user.getActive()) {
            logger.warn("Login failed: account not active: {}", email);
            throw new LoginFailedException("Your account is not active. Please check your email for the activation link.");
        }

        if (!user.getPassword().equals(password)) {
            logger.warn("Login failed: invalid credentials for email={}", email);
            throw new LoginFailedException("Invalid credentials");
        }

        String quoteText = null;
        try {
            logger.info("User {} authenticated successfully — fetching random quote", email);
            String url = "https://dummyjson.com/quotes/random";
            @SuppressWarnings("unchecked")
            Map<String, Object> resp = restTemplate.getForObject(url, Map.class);
            if (resp != null) {
                Object quoteObj = resp.get("quote");
                Object authorObj = resp.get("author");
                quoteText = (quoteObj != null ? quoteObj.toString() : "") + (authorObj != null ? " — " + authorObj.toString() : "");
                logger.debug("Fetched quote for {}: {}", email, quoteText);
            } else {
                logger.warn("Quote API returned null response for email={}", email);
            }
        } catch (RestClientException e) {
            logger.error("Failed to fetch quote after successful login for email={}", email, e);
            throw new QuoteFetchException("Failed to retrieve quote after login", e);
        } catch (Exception e) {
            logger.error("Unexpected error while fetching quote for email={}", email, e);
            throw new QuoteFetchException("Unexpected error while fetching quote after login", e);
        }

        String baseMsg = "Login successful";
        if (quoteText != null && !quoteText.isEmpty()) {
            return baseMsg + " | Quote: " + quoteText;
        }
        return baseMsg;
    }

    @Override
    public String resetPassword(PassResetDTO passResetDTO) {
        String email = passResetDTO.getEmail();
        String oldPassword = passResetDTO.getOldPassword();
        String newPassword = passResetDTO.getNewPassword();
        String confirmPassword = passResetDTO.getConfirmPassword();
        UserRegistration user = userRegistrationRepo.findByEmail(email);
        if (user == null)
            throw new LoginFailedException("Email does not exist");
        else if (!user.getPassword().equals(oldPassword)) {
            throw new LoginFailedException("Old password is incorrect");
        } else if (!newPassword.equals(confirmPassword)) {
            throw new LoginFailedException("newPassword & confirmPassword do not match");
        }
        user.setPassword(newPassword);
        user.setActive(true);
        userRegistrationRepo.save(user);
        return "Password reset successful";
    }

    public List<UserRegistration> getAllUsers() {
        logger.info("Get all users");
        return userRegistrationRepo.findAll();
    }

    public UserRegistration getUserByEmail(String email) {
        UserRegistration user = userRegistrationRepo.findByEmail(email);
        if (user == null) {
            logger.error("User not found for email={}", email);
            throw new RuntimeException("Email does not exist");
        }
        return user;
    }
}
