package br.com.itinerario.controller;

import java.util.List;

import br.com.itinerario.model.User;
import br.com.itinerario.persists.UserDAO;

public class UserController {
	
	public void salvar(String email, String senha) throws Exception {
		User u = new User(email, senha);
		UserDAO dao = new UserDAO();
		dao.create(u);
	}
	
	public User loginUsuario(String email, String senha) throws Exception {
		User u = new User(email, senha);
		UserDAO dao = new UserDAO();
		return dao.login(u);
	}
	
	public List<User> listarUsuarios() throws Exception {
		return new UserDAO().listar();
	}
}
