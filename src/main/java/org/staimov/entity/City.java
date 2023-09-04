package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "city", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class City {
    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
