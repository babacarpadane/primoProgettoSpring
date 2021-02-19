package it.objectmethod.first.maven.dao;

import it.objectmethod.first.maven.models.City;

public interface CityDao {
	public City findCityByName (String name);
}
