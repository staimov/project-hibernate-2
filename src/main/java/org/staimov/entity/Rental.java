package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rental", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Rental extends UpdateDetails {
    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
