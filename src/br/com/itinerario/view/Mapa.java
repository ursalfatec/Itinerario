package br.com.itinerario.view;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class Mapa extends PApplet{
	private UnfoldingMap map;
	
	public void setup() {
		size(320, 640);
		map = new UnfoldingMap(this, new Microsoft.HybridProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		
		float geoX = -23.520526f;
		float geoY = -46.475686f;
		
		Location berlinLocation = new Location(geoX,geoY);
		map.zoomAndPanTo(berlinLocation, 20);
		float maxPanningDistance = 5; // in km
		map.setPanningRestriction(berlinLocation, maxPanningDistance);
	}
	
	public void draw() {
		map.draw();
	}
}
