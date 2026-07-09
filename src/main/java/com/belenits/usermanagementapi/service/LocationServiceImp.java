package com.belenits.usermanagementapi.service;

import com.belenits.usermanagementapi.dto.CitiesDTO;
import com.belenits.usermanagementapi.dto.CountryDTO;
import com.belenits.usermanagementapi.dto.StatesDTO;
import com.belenits.usermanagementapi.entity.Cities;
import com.belenits.usermanagementapi.entity.Country;
import com.belenits.usermanagementapi.entity.States;
import com.belenits.usermanagementapi.exception.CountryNotFoundException;
import com.belenits.usermanagementapi.repo.CitiesRepo;
import com.belenits.usermanagementapi.repo.CountryRepo;
import com.belenits.usermanagementapi.repo.StatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImp implements LocationService{
    @Autowired
    private CitiesRepo citiesRepo;
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private StatesRepo statesRepo;

    @Override
    public List<CountryDTO> getAllCountries() {
        List<Country> countryList = countryRepo.findAll();
        List<CountryDTO> countryDTOList = new ArrayList<>();
        for (Country country : countryList) {
            CountryDTO countryDTO = CountryDTO.builder().countryname(country.getCountryname()).build();
            countryDTOList.add(countryDTO);
        }
        return countryDTOList;
    }

    @Override
    public List<StatesDTO> getAllStates(String countryName) {
        Country country = countryRepo.findByCountryname(countryName);
        if(country == null){
            throw new CountryNotFoundException("Country not found");
        }
        List<States> statesList = statesRepo.findByCountryCountryid(country.getCountryid());
        List<StatesDTO> statesDTOList = new ArrayList<>();
        for (States state : statesList) {
            StatesDTO stateDTO = StatesDTO.builder().stateName(state.getName()).build();
            statesDTOList.add(stateDTO);
        }
        return statesDTOList;
    }

    @Override
    public List<CitiesDTO> getAllCities(String countryName, String stateName) {
        Country country = countryRepo.findByCountryname(countryName);
        if(country == null){
            throw new CountryNotFoundException("Country not found");
        }
        States state = statesRepo.findByNameAndCountryCountryid(stateName, country.getCountryid());
        if(state == null){
            throw new CountryNotFoundException("State not found");
        }
        List<Cities> citiesList = citiesRepo.findByStateId(state.getId());
        List<CitiesDTO> citiesDTOList = new ArrayList<>();
        for (Cities city : citiesList) {
            CitiesDTO cityDTO = CitiesDTO.builder().cityName(city.getCity()).build();
            citiesDTOList.add(cityDTO);
        }
        return citiesDTOList;
    }
}
