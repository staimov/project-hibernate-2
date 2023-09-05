package org.staimov.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Column(name = "description", columnDefinition = "text")
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

    @Column(name = "rating",
            columnDefinition = "enum('G','PG','PG-13','R','NC-17')")
    @Convert(converter = RatingAttributeConverter.class)
    private Rating rating;

    @Column(name = "special_features",
            columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String specialFeatures;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    @ToString.Exclude
    private Set<Category> categories;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "film_actor",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
    @ToString.Exclude
    private Set<Actor> actors;

    public Set<SpecialFeature> getSpecialFeatures() {
        List<String> splitStrings = Stream.of(specialFeatures.split(",", -1))
                .collect(Collectors.toList());

        Set<SpecialFeature> result = new HashSet<>();

        for (String str: splitStrings) {
            SpecialFeature feature = SpecialFeature.getByLabel(str);
            if (feature != null) {
                result.add(feature);
            }
        }

        return result;
    }

    public void setSpecialFeatures(List<SpecialFeature> features) {
        if (features == null) {
            specialFeatures = null;
        } else {
            specialFeatures = features.stream()
                    .map(SpecialFeature::getLabel)
                    .collect(Collectors.joining(","));
        }
    }
}
