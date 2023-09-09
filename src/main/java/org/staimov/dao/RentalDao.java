package org.staimov.dao;

import org.staimov.entity.Rental;

public interface RentalDao extends GenericDao<Rental, Integer> {
    Rental getAnyUnreturned();
}
