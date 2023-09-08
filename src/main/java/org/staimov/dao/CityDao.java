package org.staimov.dao;

import org.staimov.entity.City;

public interface CityDao extends GenericDao<City, Short>  {
    City getByName(String cityName, String countryName);
}
