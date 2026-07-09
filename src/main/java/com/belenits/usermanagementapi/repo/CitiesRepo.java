package com.belenits.usermanagementapi.repo;

import com.belenits.usermanagementapi.entity.Cities;
import com.belenits.usermanagementapi.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepo extends JpaRepository<Cities, Integer> {

    List<Cities> findByStateId(Integer id);
}
