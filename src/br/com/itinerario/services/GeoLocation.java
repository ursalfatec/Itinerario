package br.com.itinerario.services;

public class GeoLocation {
	private float px;
	private float py;
	
	public GeoLocation(float px, float py) {
		this.px = px;
		this.py = py;
	}
	
	public float getX() {
		return this.px;
	}
	
	public float getY() {
		return this.py;
	}
}