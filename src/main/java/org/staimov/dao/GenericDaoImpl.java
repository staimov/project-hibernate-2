package org.staimov.dao;

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public abstract class GenericDaoImpl<V, K extends Serializable> implements GenericDao<V, K> {
    private final Class<V> clazz;
    private final SessionFactory sessionFactory;

    public GenericDaoImpl(final Class<V> clazzToSet, SessionFactory sessionFactory) {
        this.clazz = clazzToSet;
        this.sessionFactory = Preconditions.checkNotNull(sessionFactory);
    }

    @Override
    public V getOne(final K id) {
        return (V) getCurrentSession().get(clazz, id);
    }

    @Override
    public List<V> getAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public V getAny() {
        return (V) getPage(0, 1).get(0);
    }

    @Override
    public List<V> getPage(int offset, int count) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(count);
        return query.list();
    }

    @Override
    public V save(final V entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public V update(final V entity) {
        Preconditions.checkNotNull(entity);
        return (V) getCurrentSession().merge(entity);
    }

    @Override
    public void delete(final V entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().remove(entity);
    }

    @Override
    public void deleteById(final K id) {
        final V entity = getOne(id);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
