package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
