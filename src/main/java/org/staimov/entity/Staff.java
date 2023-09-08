package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "staff", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Staff extends UpdateDetails {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name="address_id", nullable = false)
    private Address address;

    @Lob
    @Column(name = "picture", columnDefinition = "blob")
    @ToString.Exclude
    private byte[] picture;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name="store_id", nullable = false)
    @ToString.Exclude
    private Store store;

    @Column(name = "active", nullable = false)
    @Type(type="org.hibernate.type.BooleanType")
    private boolean active;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password")
    private String password;
}
