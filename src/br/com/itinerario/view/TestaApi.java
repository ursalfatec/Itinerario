package br.com.itinerario.view;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.itinerario.services.OlhoVivo;

public class TestaApi {

	public static void main(String[] args) {
		try {
			//JSONArray linha = new JSONArray(OlhoVivo.getInstance().buscarLinha("273x"));
			//System.out.println(linha.getJSONObject(1).get("tp"));
			System.out.println(OlhoVivo.getInstance().buscarLinha("273x"));
			//System.out.println(OlhoVivo.getInstance().buscarParada("haia"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
