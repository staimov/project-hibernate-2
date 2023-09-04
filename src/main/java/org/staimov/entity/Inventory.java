package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Inventory extends UpdateDetails {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="film_id", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;
}
