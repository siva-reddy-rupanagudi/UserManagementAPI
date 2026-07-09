package com.belenits.usermanagementapi.service;

import com.belenits.usermanagementapi.dto.CitiesDTO;
import com.belenits.usermanagementapi.dto.CountryDTO;
import com.belenits.usermanagementapi.dto.StatesDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LocationService {
    public List<CountryDTO> getAllCountries();
    public List<StatesDTO> getAllStates(String countryName);
    public List<CitiesDTO> getAllCities(String countryName, String stateName);

}
