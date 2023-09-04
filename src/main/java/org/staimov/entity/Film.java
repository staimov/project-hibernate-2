package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "film", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Film extends UpdateDetails {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
}
