package it.objectmethod.first.maven.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.objectmethod.first.maven.dao.NationDao;
import it.objectmethod.first.maven.models.Nation;

public class NationDaoImpl implements NationDao {

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
	public Nation findNation(String nationName, String continentName) {
		Nation nationFound = new Nation();
		Connection conn = getConnection();
		String sql = "SELECT * FROM world.country WHERE Name = ? AND Continent = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, nationName);
			stmt.setString(2, continentName);
			ResultSet table = stmt.executeQuery();
			while (table.next()) {
				nationFound.setCode(table.getString("Code"));
				nationFound.setName(table.getString("Name"));
				nationFound.setContinent(table.getString("Continent"));
				nationFound.setPopulation(table.getLong("Population"));
				nationFound.setSurfaceArea(table.getLong("SurfaceArea"));
			}
			table.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nationFound;
	}

}
