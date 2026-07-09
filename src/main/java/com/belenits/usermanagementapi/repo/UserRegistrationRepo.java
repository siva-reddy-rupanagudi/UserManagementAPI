package com.belenits.usermanagementapi.repo;

import com.belenits.usermanagementapi.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRegistrationRepo extends JpaRepository<UserRegistration, Integer> {
    public UserRegistration findByEmail(String email);
}
