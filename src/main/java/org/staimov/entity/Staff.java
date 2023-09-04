package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Staff {
    @Id
    @Column(name = "staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
