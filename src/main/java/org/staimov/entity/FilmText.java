package org.staimov.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "film_text", schema = "movie")
@Getter
@Setter
@ToString
public class FilmText {
    @Id
    @Column(name = "film_id")
    private short id;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    @Type(type="text")
    private String description;
}
