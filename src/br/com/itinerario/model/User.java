package br.com.itinerario.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private String email;
	private String senha;
	
	public User(String email, String senha) throws Exception {
		this.setEmail(email);
		this.setSenha(senha);		
	}
	
	public void setEmail(String email) throws Exception {
		if(isValidEmail(email)) {
			this.email = email;
		}
	}
	
	private boolean isValidEmail(String email) throws Exception {
		boolean isEmailValid = false;
		if(email != null && email.length() > 0) {
			String expression = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			        			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if(matcher.matches()) {
				isEmailValid = true;
			}else {
				throw new RuntimeException("E-mail inválido!");
			}
		}
		return isEmailValid;
	}	
	
	public void setSenha(String senha) throws Exception {
		if(senha.length() < 8) {
			throw new RuntimeException("A senha deve ter no minímo 8 caracteres!");
		}				
		this.senha = encryptPassword(senha);
	}
	
	private String encryptPassword(String senha) throws Exception{
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(), 0, senha.length());
		return new BigInteger(1, m.digest()).toString(16);
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getSenha() {
		return this.senha;
	}
}