package com.belenits.usermanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationDTO {
    private String fullName;
    private String email;
    private String phone;
    private String country;
    private String state;
    private String city;
}
