package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "film", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Film {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


}
