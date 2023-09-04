package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Category extends UpdateDetails {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(name = "name", nullable = false)
    private String name;
}
