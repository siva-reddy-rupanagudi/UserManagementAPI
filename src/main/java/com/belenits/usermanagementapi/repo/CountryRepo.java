package com.belenits.usermanagementapi.repo;

import com.belenits.usermanagementapi.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepo extends JpaRepository<Country, Integer> {
    Country findByCountryname(String countryName);
}
