package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "language", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Language {
    @Id
    @Column(name = "language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
