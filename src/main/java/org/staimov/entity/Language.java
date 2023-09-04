package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "language", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Language extends UpdateDetails {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(name = "name", columnDefinition = "char", nullable = false)
    private String name;
}
