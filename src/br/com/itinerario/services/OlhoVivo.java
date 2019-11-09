package br.com.itinerario.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Consumir o WebService Olho vivo da sptrans, buncando informa��es sobre os �nibus e paradas
 * da cidade de S�o Paulo
 * @author FELIPE DE OLIVEIRA
 *
 */
public class OlhoVivo {
	
	private static volatile OlhoVivo uniqueInstance; 
	
	private final String TOKEN = "8897994ad6790783428b8b4dc83e649dbdf34fa603f3e0fc1e6e6d77eb34e5e8";
	
	private final String API = "http://api.olhovivo.sptrans.com.br/v2.1";
	
	private CloseableHttpClient client;
	
	/**
	 * Construtor da classe OlhoVivo.
	 * S� deve ser chamado pelo m�todo getInstance(), mantendo o padr�o de projeto Singleton.
	 * Ao ser chamado, inicia um novo client http e logo em seguida solicita autentica��o na API Olho Vivo
	 */
	private OlhoVivo() {
		client = HttpClients.createDefault();
		authenticate();
	}
	/**
	 * Retorna a refer�ncia a �nica inst�ncia da classe OlhoVivo
	 * @return Inst�ncia da classe OlhoVivo permitindo utilizar os m�todos da API Olho Vivo
	 */
	public static OlhoVivo getInstance() {
		if(uniqueInstance == null) {
			synchronized(OlhoVivo.class) {
				if(uniqueInstance == null) {
					uniqueInstance = new OlhoVivo();
				}
			}
		}
		return uniqueInstance;
	}
	/**
	 * Executa um chamado HTTP
	 * @param http
	 * @return Retorna um InputStream
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private InputStream execute(HttpUriRequest http) throws ClientProtocolException, IOException {
		return client.execute(http).getEntity().getContent();		
	}
	/**
	 * Faz uma chamada para uma url com o m�todo http post.
	 * @param url
	 * @return Uma Stream com o resultado da chamada
	 * @throws Exception
	 */
	private InputStream doPost(String url) throws Exception {
		return execute(new HttpPost(url));		
	}
	/**
	 * Faz uma chamada para uma url com o m�todo http get
	 * @param url
	 * @return Uma Stream com o resultado da chamada
	 * @throws Exception
	 */
	private InputStream doGet(String url) throws Exception {
		return execute(new HttpGet(url));		
	}	
	/**
	 * Faz a autentica��o do cliente na API Olho vivo
	 */
	private void authenticate() {
		String auth = API+"/Login/Autenticar?token="+TOKEN;		
		try {
			doPost(auth);			
		} catch (Exception e) {
			throw new RuntimeException("ERRO NA AUTENTICA��O DA API! erro:"+e.getMessage());
		}
	}
	/**
	 * Converte um InputStream em String	
	 * @param reader
	 * @return Uma String com o conte�do do InputStream 
	 * @throws IOException
	 */
	private String responseToString(InputStream reader) throws Exception {		
		StringBuilder rs = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(reader));
		String line = "";
		while((line = rd.readLine()) != null) {
			rs.append(line);
		}
		return rs.toString();
	}
	///// INTERFACE DA API OLHO VIVO /////////////////////////////////////////////////////
	/**
	 * Busca uma linha de �nibus a partir do n�mero da linha ou uma parte do nome
	 * @param termoBusca
	 * @return Uma String na nota��o JSON com um array com todas as linha encontradas
	 * @throws Exception
	 * @throws IOException
	 */
	public String buscarLinha(String termoBusca) throws Exception {		
		String search = API+"/Linha/Buscar?termosBusca="+termoBusca;
		return responseToString(doGet(search));		
	}
	/**
	 * Busca por uma parada de �nibus a partir de uma parte do endere�o	
	 * @param termoBusca
	 * @return Uma String na nota��o JSON com um array com todas as paradas encontradas
	 * @throws Exception
	 * @throws IOException
	 */
	public String buscarParada(String termoBusca) throws Exception {
		String search = API+"/Parada/Buscar?termosBusca="+termoBusca;
		return responseToString(doGet(search));	
	}
	/**
	 * Retorna uma lista com todos os veículos de uma determinada linha com suas devidas posições
	 * @param codigoLinha
	 * @return Um objeto JSON com um atributo vs, que contém lat e lng de todos os veiculos da linha
	 * @throws Exception
	 */
	public String posicaoDaLinha(int codigoLinha) throws Exception {
		String search = API+"/Posicao/Linha?codigoLinha="+codigoLinha;
		return responseToString(doGet(search));	
	}
}