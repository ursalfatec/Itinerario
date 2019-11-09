package br.com.itinerario.model;

public abstract class Posicao {
	private float px;
	private float py;
	
	public float getPx() {
		return px;
	}
	public void setPx(float px) {
		this.px = px;
	}
	public float getPy() {
		return py;
	}
	public void setPy(float py) {
		this.py = py;
	}	
}