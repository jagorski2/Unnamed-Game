package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;




public class Main {
	private final static String USER_AGENT = "Mozilla/5.0";
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setId(1);
		user.setPassword("");
		user.setUserid("");
		ObjectMapper mapper = new ObjectMapper();
		String url = "http://localhost:9000/yes";
		
		//method two
		HttpClient httpClient = new DefaultHttpClient();
		try {
			
		
		HttpPost request = new HttpPost(url);
		StringEntity urlParams = new StringEntity(mapper.writeValueAsString(user));
		request.addHeader("content-type", "application/json");
		request.setEntity(urlParams);
		HttpResponse response = httpClient.execute(request);
		InputStream s = response.getEntity().getContent();
		long size = response.getEntity().getContentLength();
		byte[] bytes = new byte[(int) size];
			s.read(bytes, 0, (int) size);
		String string = new String(bytes);
		
		
		
		user = mapper.readValue(string, User.class);
		System.out.println(user.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		

		
		
		
		
		
		
		
		
		
		
		
		/*
		//method one
		URL obj = new URL(url);
		URLConnection con =  obj.openConnection();
		//add reuqest header
		//con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = mapper.writeValueAsString(user);
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		mapper.writeValue(con.getOutputStream(),user);
		//wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		//int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		//System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 */
		//print result

	}
}
