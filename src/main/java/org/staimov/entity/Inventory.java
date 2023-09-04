package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Inventory {
    @Id
    @Column(name = "inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
