package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.staimov.entity.Payment;

public class PaymentDaoImpl extends GenericDaoImpl<Payment, Short> implements PaymentDao {
    public PaymentDaoImpl(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
