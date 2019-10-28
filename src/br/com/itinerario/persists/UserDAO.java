package br.com.itinerario.persists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.itinerario.model.User;

public class UserDAO implements IDAO<User>{
	private Connection conn;
	
	public UserDAO() {
		this.conn = new ConnectionFactory().getConnection();
		new CreateUserTable().create();
	}
	
	private void close(Statement stmt) {
		try {
			this.conn.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	public void create(Object o) throws Exception {
		User u = (User)o;
		String sql = "INSERT INTO USER (EMAIL,SENHA) VALUES (?,?)";		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, u.getEmail());
		stmt.setString(2, u.getSenha());
		stmt.executeUpdate();
		
		close(stmt);
	}

	@Override
	public void update(Object o) throws Exception {
		User u = (User)o;
		String sql = "UPDATE USER SET EMAIL = ?,SENHA = ? WHERE ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, u.getEmail());
		stmt.setString(2, u.getSenha());
		stmt.setInt(3, u.getId());
		stmt.executeUpdate();		
		close(stmt);
	}

	@Override
	public void delete(Object o) throws Exception {
		User u = (User)o;
		String sql = "DELETE FROM USER WHERE ID = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, u.getId());		
		stmt.executeUpdate();		
		close(stmt);
	}

	@Override
	public List<User> listar() throws Exception {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM USER";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			User u = new User(rs.getString("EMAIL"), rs.getString("SENHA"));
			u.setId(rs.getInt("ID"));
			users.add(u);
		}
		rs.close();
		close(stmt);
		return users;
	}
	
	public User login(User u) throws Exception {
		String sql = "SELECT * FROM USER WHERE EMAIL = ? AND SENHA = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, u.getEmail());
		stmt.setString(2, u.getSenha());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			User user = new User(rs.getString("EMAIL"), rs.getString("SENHA"));
			user.setId(rs.getInt("ID"));
			return user;
		}
		close(stmt);
		return null;
	}

}
