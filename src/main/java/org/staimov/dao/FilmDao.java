package org.staimov.dao;

import org.staimov.entity.Film;

import org.hibernate.SessionFactory;

public class FilmDao extends AbstractDao<Film, Short> {
    public FilmDao(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
