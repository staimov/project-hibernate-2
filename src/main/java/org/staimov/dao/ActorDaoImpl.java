package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.staimov.entity.Actor;

public class ActorDaoImpl extends GenericDaoImpl<Actor, Short> implements ActorDao {
    public ActorDaoImpl(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
