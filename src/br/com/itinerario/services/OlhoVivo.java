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
 * Consumir o WebService Olho vivo da sptrans, buncando informações sobre os ônibus e paradas
 * da cidade de São Paulo
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
	 * Só deve ser chamado pelo método getInstance(), mantendo o padrão de projeto Singleton.
	 * Ao ser chamado, inicia um novo client http e logo em seguida solicita autenticação na API Olho Vivo
	 */
	private OlhoVivo() {
		client = HttpClients.createDefault();
		authenticate();
	}
	/**
	 * Retorna a referência a única instância da classe OlhoVivo
	 * @return Instância da classe OlhoVivo permitindo utilizar os métodos da API Olho Vivo
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
	 * Faz uma chamada para uma url com o método http post.
	 * @param url
	 * @return Uma Stream com o resultado da chamada
	 * @throws Exception
	 */
	private InputStream doPost(String url) throws Exception {
		return execute(new HttpPost(url));		
	}
	/**
	 * Faz uma chamada para uma url com o método http get
	 * @param url
	 * @return Uma Stream com o resultado da chamada
	 * @throws Exception
	 */
	private InputStream doGet(String url) throws Exception {
		return execute(new HttpGet(url));		
	}	
	/**
	 * Faz a autenticação do cliente na API Olho vivo
	 */
	private void authenticate() {
		String auth = API+"/Login/Autenticar?token="+TOKEN;		
		try {
			doPost(auth);			
		} catch (Exception e) {
			throw new RuntimeException("ERRO NA AUTENTICAÇÃO DA API! erro:"+e.getMessage());
		}
	}
	/**
	 * Converte um InputStream em String	
	 * @param reader
	 * @return Uma String com o conteúdo do InputStream 
	 * @throws IOException
	 */
	private String responseToString(InputStream reader) throws IOException {		
		StringBuilder rs = new StringBuilder();
		BufferedReader rd = new BufferedReader(new InputStreamReader(reader));
		String line = "";
		while((line = rd.readLine()) != null) {
			rs.append(line);
		}
		return rs.toString();
	}
	/**
	 * Busca uma linha de ônibus a partir do número da linha ou uma parte do nome
	 * @param termoBusca
	 * @return Uma String na notação JSON com um array com todas as linha encontradas
	 * @throws Exception
	 * @throws IOException
	 */
	public String buscarLinha(String termoBusca) throws Exception, IOException {		
		String search = API+"/Linha/Buscar?termosBusca="+termoBusca;
		return responseToString(doGet(search));		
	}
	/**
	 * Busca por uma parada de ônibus a partir de uma parte do endereço	
	 * @param termoBusca
	 * @return Uma String na notação JSON com um array com todas as paradas encontradas
	 * @throws Exception
	 * @throws IOException
	 */
	public String buscarParada(String termoBusca) throws Exception, IOException {
		String search = API+"/Parada/Buscar?termosBusca="+termoBusca;
		return responseToString(doGet(search));	
	}		
}