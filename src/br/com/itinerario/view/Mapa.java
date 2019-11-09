package br.com.itinerario.view;

import br.com.itinerario.controller.LinhaController;
import br.com.itinerario.controller.OnibusController;
import br.com.itinerario.model.Linha;
import br.com.itinerario.model.Onibus;
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
		Location onibusLocation;
		SimplePointMarker onibusMarker = null;
		try {
			/*Chamar os Controllers para onibus e linha*/
			LinhaController ctl = new LinhaController();
			OnibusController octl = new OnibusController();
			
			/*Pesquisar por uma linha*/
			ctl.buscarLinha("2725");
			
			// Executa um laço para cada linha que retornou da pesquisa
			for(Linha l : ctl.exibirLinhas()) {
				// Aqui mostra o Letreiro completo da linha
				System.out.println(l.getLetreiroCompleto());
				// Verifica o sentido da linha para mostrar o letreiro completo
				if(l.getSentidoLinha() == 1) {
					System.out.println(l.getLetreiroSecundario());
				}else {
					System.out.println(l.getLetreiroPrincipal());
				}
				// Aqui, executa um laço que busca a posição de cada ônibus da linha retornada pela pesquisa
				for(Onibus o : octl.buscarPosicaoDosOnibusDaLinha(l.getCodigoLinha())) {
					// O objeto da classe Onibus possui os métodos getPx e getPy que retornam as coordenadas
					System.out.println(o.getPx()+" : "+o.getPy());
					onibusLocation = new Location(o.getPx(), o.getPy());
					onibusMarker = new SimplePointMarker(onibusLocation);
				}			
				System.out.println("--------------------------------------------------------");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Localizaçăo do usuário:
		float usuarioGeoX = -23.52091f;
		float usuarioGeoY = -46.475555f;
		Location usuárioLocation = new Location(usuarioGeoX, usuarioGeoY);

		// Localizaçăo do ônibus:
		//float onibusGeoX = -23.521976f;
		//float onibusGeoY = -46.475376f;
		//Location onibusLocation = new Location(onibusGeoX, onibusGeoY);

		// PARAMETROS DE INICIALIZAÇĂO DO MAPA:

		map.zoomAndPanTo(usuárioLocation, 20);
		float maxPanningDistance = 1.5f; // in km
		map.setPanningRestriction(usuárioLocation, maxPanningDistance);

		// MARCADORES:

		// usuarioMarker
		SimplePointMarker usuarioMarker = new SimplePointMarker(usuárioLocation);

		// onibusMarker
		

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