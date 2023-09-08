package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.staimov.entity.Address;

public class AddressDaoImpl extends GenericDaoImpl<Address, Short> implements AddressDao {
    public AddressDaoImpl(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
