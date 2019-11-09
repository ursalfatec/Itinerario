package br.com.itinerario.model;

import java.util.ArrayList;
import java.util.List;

public class Linha {
	private Integer codigoLinha;
	private Integer sentidoLinha;
	private String letreiroCompleto;
	private String letreiroPrincipal;
	private String letreiroSecundario;	
	private List<Onibus> onibus;
		
	public Integer getCodigoLinha() {
		return codigoLinha;
	}
	
	public void setCodigoLinha(Integer codigoLinha) {
		this.codigoLinha = codigoLinha;
	}
	
	public Integer getSentidoLinha() {
		return sentidoLinha;
	}

	public void setSentidoLinha(Integer sentidoLinha) {
		this.sentidoLinha = sentidoLinha;
	}
	
	public String getLetreiroCompleto() {
		return letreiroCompleto;
	}
	
	public void setLetreiroCompleto(String letreiroCompleto) {
		this.letreiroCompleto = letreiroCompleto;
	}
	
	public String getLetreiroSecundario() {
		return letreiroSecundario;
	}
	
	public void setLetreiroSecundario(String letreiroSecundario) {
		this.letreiroSecundario = letreiroSecundario;
	}
	
	public String getLetreiroPrincipal() {
		return letreiroPrincipal;
	}
	
	public void setLetreiroPrincipal(String letreiroPrincipal) {
		this.letreiroPrincipal = letreiroPrincipal;
	}
	
	public List<Onibus> listarOnibus() {
		List<Onibus> onibus = new ArrayList<Onibus>();
		for(Onibus o : this.onibus) {
			onibus.add(o);
		}
		return onibus;
	}
	
	public void setPosicoesDosOnibus(List<Onibus> onibus) {
		for(Onibus o : onibus) {
			//onibus.add(o);
			System.out.println(o.getPx());
		}
		//this.onibus = onibus;
	}
}