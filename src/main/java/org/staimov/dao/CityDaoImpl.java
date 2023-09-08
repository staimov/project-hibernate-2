package org.staimov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.staimov.entity.City;

public class CityDaoImpl extends GenericDaoImpl<City, Short> implements CityDao {
    public CityDaoImpl(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    @Override
    public City getByName(String cityName, String countryName) {
            Query<City> query = getCurrentSession().createQuery(
                    "from City where name = :cityName and country.name = :countryName", City.class);
            query.setParameter("cityName", cityName);
            query.setParameter("countryName", countryName);
            return query.uniqueResult();
    }
}
