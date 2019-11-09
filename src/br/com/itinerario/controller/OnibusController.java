package br.com.itinerario.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.itinerario.services.OlhoVivo;
import br.com.itinerario.model.Onibus;

public class OnibusController {
	
	public List<Onibus> buscarPosicaoDosOnibusDaLinha(int codigoDaLinha) throws Exception {
		List<Onibus> posicaoDosOnibus = new ArrayList<Onibus>();
		String response = OlhoVivo.getInstance().posicaoDaLinha(codigoDaLinha);
		
		JSONObject obj = new JSONObject(response);
		JSONArray array = obj.getJSONArray("vs");
		
		for(int i = 0;i < array.length();i++) {
			JSONObject veiculo = array.getJSONObject(i);
			Onibus o = new Onibus();
			o.setPx(veiculo.getFloat("px"));
			o.setPy(veiculo.getFloat("py"));
			
			posicaoDosOnibus.add(o);
		}
		
		return posicaoDosOnibus;
	}
}
