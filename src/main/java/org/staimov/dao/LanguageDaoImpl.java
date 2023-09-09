package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.staimov.entity.Language;

public class LanguageDaoImpl extends GenericDaoImpl<Language, Byte> implements LanguageDao {
    public LanguageDaoImpl(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
