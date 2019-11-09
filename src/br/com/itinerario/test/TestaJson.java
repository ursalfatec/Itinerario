package br.com.itinerario.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class TestaJson {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://viacep.com.br/ws/08061440/json/");
			InputStream is = url.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			StringBuilder out = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				out.append(line);
			}
			System.out.println(out.toString());
			reader.close();
		} catch (MalformedURLException e) {
			System.out.println("Url incorreta");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Ocorreu um erro na leitura da api");
			e.printStackTrace();
		}
	}

}
