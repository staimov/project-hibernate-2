package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Store extends UpdateDetails {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @OneToOne
    @JoinColumn(name="address_id", nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name="manager_staff_id", nullable = false)
    private Staff manager;
}
