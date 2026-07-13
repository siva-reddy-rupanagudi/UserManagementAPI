package com.belenits.usermanagementapi.mappers;


import com.belenits.usermanagementapi.dto.UserRegistrationDTO;
import com.belenits.usermanagementapi.entity.UserRegistration;
import org.modelmapper.ModelMapper;

public class ModelMappers {

    public static UserRegistrationDTO toUserRegistrationDTO(UserRegistration user) {
        ModelMapper modelMapper = new ModelMapper();
        UserRegistrationDTO dto = modelMapper.map(user, UserRegistrationDTO.class);
        return dto;
    }
    public static UserRegistration toUserRegistration(UserRegistrationDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        UserRegistration userRegistration = modelMapper.map(dto, UserRegistration.class);
        return userRegistration;
    }
}
