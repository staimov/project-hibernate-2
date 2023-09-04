package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
