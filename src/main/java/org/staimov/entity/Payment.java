package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment", schema = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Payment {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
}
