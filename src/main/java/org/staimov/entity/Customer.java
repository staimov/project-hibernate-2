package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Customer extends UpdateDetails {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
}
