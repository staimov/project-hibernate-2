package org.staimov.dao;

import org.staimov.entity.Film;

import org.hibernate.SessionFactory;

public class FilmDaoImpl extends GenericDaoImpl<Film, Short> implements FilmDao {
    public FilmDaoImpl(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

    @Override
    public Film getFirstAvailableFilmForRent() {
        return null;
    }
}
