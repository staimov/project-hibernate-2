package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Country extends UpdateDetails {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "country", nullable = false)
    private String name;
}
