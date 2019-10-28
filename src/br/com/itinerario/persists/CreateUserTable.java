package br.com.itinerario.persists;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUserTable {
	public void create() {
		Connection conn = new ConnectionFactory().getConnection();
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("CREATE TABLE IF NOT EXISTS USER(");
			sql.append("ID INTEGER PRIMARY KEY AUTOINCREMENT,");
			sql.append("EMAIL VARCHAR(50) NOT NULL,");
			sql.append("SENHA VARCHAR(50) NOT NULL)");
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
