package org.staimov.dao;

import org.hibernate.query.Query;
import org.staimov.entity.City;
import org.staimov.entity.Film;

import org.hibernate.SessionFactory;

public class FilmDaoImpl extends GenericDaoImpl<Film, Short> implements FilmDao {
    public FilmDaoImpl(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
