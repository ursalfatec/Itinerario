package br.com.itinerario.test;

import br.com.itinerario.model.User;

public class TestaUser {

	public static void main(String[] args) {
		try {
			User user = new User("felipe@gmail.com", "e8dc4081b13434b45189a720b77b6818");
			System.out.println(user.getEmail()+", "+user.getSenha());
			
			//String senha1 = "25d55ad283aa400af464c76d713c07ad";//12345678
			//String senha2 = "550e1bafe077ff0b0b67f4e32f29d751";//25d55ad283aa400af464c76d713c07ad
			//System.out.println(senha1.length() == senha2.length());
			//System.out.println(senha1.length());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
