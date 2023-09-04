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
    private Integer id;
}
