package br.com.itinerario.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;


public class TestPost {

	public static void main(String[] args) throws Exception{
				
		String token = "8897994ad6790783428b8b4dc83e649dbdf34fa603f3e0fc1e6e6d77eb34e5e8";
		
		String api = "http://api.olhovivo.sptrans.com.br/v2.1/Login/Autenticar?token="+token;
		
		String busca = "http://api.olhovivo.sptrans.com.br/v2.1/Linha/Buscar?termosBusca=8000";
		
		//HttpClient client = new DefaultHttpClient();
		//client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		
		//HttpHost proxy = new HttpHost("andromeda", 3128, "http");
		
		//DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner();
		//CloseableHttpClient client = HttpClients.custom()
		  //                  .setRoutePlanner(routePlanner)
		    //                .build();
		
		CloseableHttpClient client = HttpClients.createDefault();
		// Autenticação
		HttpPost post = new HttpPost(api);
		//Buscando uma linha
		HttpGet get = new HttpGet(busca);
		
		try {
			//List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			//nameValuePairs.add(new BasicNameValuePair("token", token));
			//post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			
			
			HttpResponse response = client.execute(post);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			String line = "";
			while((line = rd.readLine()) != null) {
				System.out.println(line);
			}
			
			HttpResponse responseget = client.execute(get);
			
			Scanner sc = new Scanner(responseget.getEntity().getContent());
			while(sc.hasNext()) {
				System.out.println(sc.nextLine());
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	
	}

}
