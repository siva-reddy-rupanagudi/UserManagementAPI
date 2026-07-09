package com.belenits.usermanagementapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cities")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String city;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private States state;
}
