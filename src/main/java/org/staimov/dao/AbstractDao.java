package org.staimov.dao;

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDao<T, K extends Serializable> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public AbstractDao(final Class<T> clazzToSet, SessionFactory sessionFactory) {
        this.clazz = Preconditions.checkNotNull(clazzToSet);
        this.sessionFactory = Preconditions.checkNotNull(sessionFactory);
    }

    public T findOne(final K id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public T create(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    public void deleteById(final K entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
