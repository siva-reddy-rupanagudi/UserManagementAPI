package com.belenits.usermanagementapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "states")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class States {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @OneToMany(mappedBy = "state",cascade = CascadeType.ALL)
    private List<Cities> cities;

}
