package it.objectmethod.first.maven.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.objectmethod.first.maven.dao.CityDao;
import it.objectmethod.first.maven.models.City;

public class CityDaoImpl implements CityDao {

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:33060/", "omdev", "omdev");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	@Override
	public City findCityByName(String name) {
		Connection conn = getConnection();
		City cityFound = new City();
		String sql = "SELECT * FROM	world.city WHERE Name = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet table = stmt.executeQuery();
			while (table.next()) {
				cityFound.setId(table.getLong("ID"));
				cityFound.setName(table.getString("Name"));
				cityFound.setCountryCode(table.getString("CountryCode"));
				cityFound.setDistrict(table.getString("District"));
				cityFound.setPopulation(table.getLong("Population"));
			}
			table.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cityFound;
	}

}
