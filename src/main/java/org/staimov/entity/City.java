package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "city", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class City extends UpdateDetails {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "city", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="country_id", nullable = false)
    private Country country;
}
