package br.com.itinerario.view;

import java.sql.Connection;

import br.com.itinerario.model.User;
import br.com.itinerario.persists.ConnectionFactory;
import br.com.itinerario.persists.CreateUserTable;
import br.com.itinerario.persists.UserDAO;

public class TestaConexao {
	
	public static void main(String[] args) {
		Connection conn = new ConnectionFactory().getConnection();
		//new CreateUserTable().create();
		//System.out.println("con ok");
		try {
			User u = new User("felipe.oliveira@gmail.com", "12345678");
			UserDAO dao = new UserDAO();
			//dao.create(u);
			//System.out.println("Usu�rio Criado");
//			for(User us:dao.listar()) {
//				System.out.println("email: "+us.getEmail()+", "+us.getSenha());
//			}
			
			User user = dao.login(u);
			if(user != null) {
				System.out.println("Logado");
			}else {
				System.out.println("Usuário ou senha inválido");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
