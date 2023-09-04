package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
