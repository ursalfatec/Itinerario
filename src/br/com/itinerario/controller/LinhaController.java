package br.com.itinerario.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.itinerario.model.Linha;
import br.com.itinerario.services.OlhoVivo;

public class LinhaController {
	private List<Linha> linhas;
	
	public LinhaController() {
		this.linhas = new ArrayList();
	}
	
	public void buscarLinha(String termo) throws Exception {
		String response = OlhoVivo.getInstance().buscarLinha(termo);
		JSONArray array = new JSONArray(response);
		
		for(int i = 0;i < array.length();i++) {
			JSONObject obj = array.getJSONObject(i);
			Linha linha = new Linha();
			linha.setCodigoLinha(obj.getInt("cl"));
			linha.setSentidoLinha(obj.getInt("sl"));
			String letreiroCompleto = obj.getString("lt") + "-" + obj.getInt("tl");
			linha.setLetreiroCompleto(letreiroCompleto);
			linha.setLetreiroPrincipal(obj.getString("tp"));
			linha.setLetreiroSecundario(obj.getString("ts"));
			
			this.linhas.add(linha);
		}		
	}

	public List<Linha> exibirLinhas() {
		return this.linhas;
	}
}
