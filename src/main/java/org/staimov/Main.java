package org.staimov;

import org.staimov.entity.*;
import org.staimov.repository.MovieRepo;

public class Main {
    public static void main(String[] args) {
        MovieRepo repo = new MovieRepo();

        Film film = repo.getFilm((short) 1);
        System.out.println(film);

        Actor actor = repo.getActor((short) 1);
        System.out.println(actor);
    }
}
