package com.belenits.usermanagementapi.contoller;

import com.belenits.usermanagementapi.dto.CitiesDTO;
import com.belenits.usermanagementapi.dto.CountryDTO;
import com.belenits.usermanagementapi.dto.StatesDTO;
import com.belenits.usermanagementapi.entity.Country;
import com.belenits.usermanagementapi.response.ApiResponse;
import com.belenits.usermanagementapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/getAllCountries")
    public ResponseEntity<ApiResponse<List<CountryDTO>>> getAllCountries() {
        List<CountryDTO> countries = locationService.getAllCountries();
        ApiResponse<List<CountryDTO>> response = new ApiResponse<>();
        response.setData(countries);
        response.setMessage("Success");
        response.setStatus(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getAllStates/{countryName}")
    public ResponseEntity<ApiResponse<List<StatesDTO>>> getAllStates(@PathVariable String countryName) {
        List<StatesDTO> states = locationService.getAllStates(countryName);
        ApiResponse<List<StatesDTO>> response = new ApiResponse<>();
        response.setData(states);
        response.setMessage("Success");
        response.setStatus(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<ApiResponse<List<CitiesDTO>>> getAllCities(@RequestParam String countryName, @RequestParam String stateName) {
        List<CitiesDTO> cities = locationService.getAllCities(countryName,stateName);
        ApiResponse<List<CitiesDTO>> response = new ApiResponse<>();
        response.setData(cities);
        response.setMessage("Success");
        response.setStatus(200);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
