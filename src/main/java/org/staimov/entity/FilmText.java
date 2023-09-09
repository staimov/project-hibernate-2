package org.staimov.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
    @Setter(AccessLevel.NONE)
    private Film film;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    public void setFilm(Film film) {
        this.film = film;
        this.id = film.getId();
    }
}
