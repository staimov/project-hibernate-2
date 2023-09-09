package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.staimov.entity.FilmText;

public class FilmTextDaoImpl extends GenericDaoImpl<FilmText, Short> implements FilmTextDao {
    public FilmTextDaoImpl(SessionFactory sessionFactory) {
        super(FilmText.class, sessionFactory);
    }
}
