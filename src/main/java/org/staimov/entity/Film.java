package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;

@Entity
@Table(name = "film", schema = "movie")
@Getter
@Setter
@ToString(callSuper = true)
public class Film extends UpdateDetails {
    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    @Type(type="text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name="language_id", nullable = false)
    private Language language;

    @ManyToOne
    @JoinColumn(name="original_language_id")
    private Language originalLanguage;

    @Column(name="rental_duration", nullable = false)
    private byte rentalDuration;

    @Column(name="rental_rate", nullable = false)
    private BigDecimal rentalRate;

    @Column(name="length")
    private short length;

    @Column(name="replacement_cost",  nullable = false)
    private BigDecimal replacementCost;

//    @Column(name = "rating")
//    private String rating;

    @Column(name = "special_features",
            columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;
}
