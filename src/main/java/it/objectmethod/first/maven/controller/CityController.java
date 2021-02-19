package it.objectmethod.first.maven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.first.maven.dao.CityDao;
import it.objectmethod.first.maven.dao.NationDao;
import it.objectmethod.first.maven.dao.impl.CityDaoImpl;
import it.objectmethod.first.maven.dao.impl.NationDaoImpl;
import it.objectmethod.first.maven.models.City;
import it.objectmethod.first.maven.models.Nation;

@RestController
public class CityController {
	
	@GetMapping("/find-city")
	public City findCity(@RequestParam("nameCity") String nameCityRequested) {
		CityDao cityToFind = new CityDaoImpl();
		City finalCity = cityToFind.findCityByName(nameCityRequested);
		
		return finalCity;	
	}
	
	@GetMapping("/find-nation")
	public Nation findNation(@RequestParam("nameNation") String nameNationRequested, 
			@RequestParam("nameContinent") String nameContinentRequested) {
		NationDao nationToFind = new NationDaoImpl();
		Nation finalNation = nationToFind.findNation(nameNationRequested, nameContinentRequested);
		
		return finalNation;	
	}
}