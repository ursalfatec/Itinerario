package br.com.itinerario.test;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.itinerario.controller.LinhaController;
import br.com.itinerario.controller.OnibusController;
import br.com.itinerario.model.Linha;
import br.com.itinerario.model.Onibus;
import br.com.itinerario.services.OlhoVivo;

public class TestaApi {

	public static void main(String[] args) {
		try {
			//JSONArray linha = new JSONArray(OlhoVivo.getInstance().buscarLinha("273x"));
			//System.out.println(linha.getJSONObject(1).get("tp"));
			//System.out.println(OlhoVivo.getInstance().buscarLinha("273x"));
			//System.out.println(OlhoVivo.getInstance().buscarParada("haia"));
			
			LinhaController ctl = new LinhaController();
			OnibusController octl = new OnibusController();
			
			ctl.buscarLinha("2725");
			
			for(Linha l : ctl.exibirLinhas()) {
				System.out.println(l.getLetreiroCompleto());
				if(l.getSentidoLinha() == 1) {
					System.out.println(l.getLetreiroSecundario());
				}else {
					System.out.println(l.getLetreiroPrincipal());
				}
				for(Onibus o : octl.buscarPosicaoDosOnibusDaLinha(l.getCodigoLinha())) {
					System.out.println(o.getPx()+" : "+o.getPy());
				}			
				System.out.println("--------------------------------------------------------");
			}	
			
			//System.out.println(OlhoVivo.getInstance().posicaoDaLinha(33160));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
