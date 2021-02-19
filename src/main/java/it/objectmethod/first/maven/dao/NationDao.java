package it.objectmethod.first.maven.dao;

import it.objectmethod.first.maven.models.Nation;

public interface NationDao {
	public Nation findNation (String nationName, String continentName);
}
