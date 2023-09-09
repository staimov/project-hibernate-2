package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.staimov.entity.Rental;

public class RentalDaoImpl extends GenericDaoImpl<Rental, Integer> implements RentalDao {
    public RentalDaoImpl(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    @Override
    public Rental getAnyUnreturned() {
        Query<Rental> query = getCurrentSession().createQuery(
                "from Rental where returnDate is null", Rental.class);
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}
