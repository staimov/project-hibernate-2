package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rental", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Rental {
    @Id
    @Column(name = "rental_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
