package com.belenits.usermanagementapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassResetDTO {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
