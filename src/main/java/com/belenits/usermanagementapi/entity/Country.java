package com.belenits.usermanagementapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "country")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryid;
    private String countryname;
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL)
    private List<States> states;

}
