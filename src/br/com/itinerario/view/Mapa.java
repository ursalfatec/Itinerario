package br.com.itinerario.view;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.providers.OpenStreetMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Mapa extends PApplet {
	private UnfoldingMap map;

	public void setup() {

		// TAMANHO DA TELA E PROVEDOR DO MAPA:
		size(320, 640, OPENGL);
		// map = new UnfoldingMap(this, new Microsoft.HybridProvider());
		map = new UnfoldingMap(this, new OpenStreetMap.OpenStreetMapProvider());

		// Movimentaçăo do mapa usando o mouse:
		MapUtils.createDefaultEventDispatcher(this, map);

		// LOCALIZAÇĂO:

		// Localizaçăo do usuário:
		float usuarioGeoX = -23.52091f;
		float usuarioGeoY = -46.475555f;
		Location usuárioLocation = new Location(usuarioGeoX, usuarioGeoY);

		// Localizaçăo do ônibus:
		float onibusGeoX = -23.521976f;
		float onibusGeoY = -46.475376f;
		Location onibusLocation = new Location(onibusGeoX, onibusGeoY);

		// PARAMETROS DE INICIALIZAÇĂO DO MAPA:

		map.zoomAndPanTo(usuárioLocation, 20);
		float maxPanningDistance = 1.5f; // in km
		map.setPanningRestriction(usuárioLocation, maxPanningDistance);

		// MARCADORES:

		// usuarioMarker
		SimplePointMarker usuarioMarker = new SimplePointMarker(usuárioLocation);

		// onibusMarker
		SimplePointMarker onibusMarker = new SimplePointMarker(onibusLocation);

		// Marcadores adicionados ao Mapa
		map.addMarkers(usuarioMarker, onibusMarker);

		// ESTILOS DOS MARCADORES:

		// Estilo do usuarioMarker
		usuarioMarker.setColor(color(255, 255, 0, 100));
		usuarioMarker.setStrokeColor(color(255, 0, 0));
		usuarioMarker.setStrokeWeight(3);
		usuarioMarker.setRadius(20);

		// Estilo do onibusMarker
		onibusMarker.setColor(color(0, 255, 0, 100));
		onibusMarker.setStrokeColor(color(0, 0, 0));
		onibusMarker.setStrokeWeight(3);
		onibusMarker.setRadius(20);
	}

	// MÉTODO AUXILIAR QUE VAI SER APAGADO
	public void mouseClicked() {
		Location location = map.getLocation(mouseX, mouseY);
		System.out.printf(location.getLat() + ", " + location.getLon(), mouseX, mouseY);
	}

	public void draw() {
		map.draw();

		// COORDENADAS DO PONTEIRO DO MOUSE:
		Location location = map.getLocation(mouseX, mouseY);
		fill(0);
		text(location.getLat() + ", " + location.getLon(), mouseX, mouseY);
	}
}
