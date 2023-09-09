package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.staimov.entity.Store;

public class StoreDaoImpl extends GenericDaoImpl<Store, Byte> implements StoreDao {
    public StoreDaoImpl(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
