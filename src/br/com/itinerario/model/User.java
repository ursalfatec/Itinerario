package br.com.itinerario.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private String email;
	private String senha;
	
	public User(String email, String senha) throws Exception {
		this.setEmail(email);
		this.senha = senha;
	}
	
	public void setEmail(String email) throws Exception {
		if(isValidEmail(email)) {
			this.email = email;
		}
	}
	
	private boolean isValidEmail(String email) {
		boolean isEmailValid = false;
		if(email != null && email.length() > 0) {
			String expression = "^[\\\\w\\\\.-]+@([\\\\w\\\\-]+\\\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if(matcher.matches()) {
				isEmailValid = true;
			}
		}
		return isEmailValid;
	}	
}