package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.staimov.entity.Customer;

public class CustomerDaoImpl extends GenericDaoImpl<Customer, Short> implements CustomerDao {
    public CustomerDaoImpl(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
