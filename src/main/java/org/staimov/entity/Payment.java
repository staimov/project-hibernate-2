package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Payment extends UpdateDetails {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
}
