package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Country {
    @Id
    @Column(name = "country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
