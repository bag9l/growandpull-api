package com.growandpull.api.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`country`")
public class Country {

    @Id
    private int id;

    @Column(name = "shortname", length = 2)
    private String shortname;

    @Column(name = "`name`", length = 150, nullable = false)
    private String name;

    @Column(name = "phone_code")
    private int phoneCode;

    @OneToMany(
            mappedBy = "country",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<City> cities;
}
