package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Staff extends UpdateDetails {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;
}
