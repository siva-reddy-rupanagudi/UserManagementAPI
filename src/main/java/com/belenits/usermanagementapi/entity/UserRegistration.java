package com.belenits.usermanagementapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration {
    @Id
    @UserIdGen
    private String id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String country;
    private String state;
    private String city;
    private Boolean active=false;
    private String password;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate registrationDate;
    @UpdateTimestamp
    private LocalDate updateDate;
}
