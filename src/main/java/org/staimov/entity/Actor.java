package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "actor", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Actor {
    @Id
    @Column(name = "actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
