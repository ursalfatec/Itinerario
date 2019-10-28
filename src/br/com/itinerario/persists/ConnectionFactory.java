package br.com.itinerario.persists;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:sqlite:src/br/com/itinerario/persists/JTP.db");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
