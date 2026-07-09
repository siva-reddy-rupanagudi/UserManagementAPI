package com.belenits.usermanagementapi.repo;

import com.belenits.usermanagementapi.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatesRepo extends JpaRepository<States, Integer> {
    List<States> findByCountryCountryid(Integer countryid);

    States findByNameAndCountryCountryid(String stateName, Integer countryid);
}
