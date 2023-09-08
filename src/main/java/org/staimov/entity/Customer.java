package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Customer extends UpdateDetails {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    private Store store;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name="address_id", nullable = false)
    private Address address;

    @Column(name = "active", nullable = false)
    @Type(type="org.hibernate.type.BooleanType")
    private boolean active;

    @Column(name = "create_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createDate;
}
