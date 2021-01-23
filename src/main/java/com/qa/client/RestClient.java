package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
	
	// 1. GET Call
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient  httpClient=HttpClients.createDefault(); //create a httpClient obj
        HttpGet httpGet=new HttpGet(url); // create a httpGet obj
        CloseableHttpResponse closeableHttpResponse= httpClient.execute(httpGet); // execute the Get request
        return closeableHttpResponse;
		
	}
	
	public CloseableHttpResponse getMethod(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient=HttpClients.createDefault();
		/*
		 * HttpClients class has createDefault() method which returns a
		 * CloseableHttpClient object, which is the base implementation of the
		 * HttpClient interface.
		 */
		HttpGet httpGet=new HttpGet(url);
		/*
		 * Create a HTTP GET request by instantiating this class. The constructor of
		 * this class accepts a String value representing the URI.
		 */
		CloseableHttpResponse closableHttpResponse=httpClient.execute(httpGet);
		/* CloseableHttpClient class has execute method which accepts a httpGet, httpPost, httpPut etc
		 * and returns a response object
		 */
		return closableHttpResponse;
		
	}
	
	// 2 post call
	public CloseableHttpResponse post(String url,String entityString, HashMap<String,String> hm) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient=HttpClients.createDefault();  // creating httpClient
		HttpPost httpPost=new HttpPost(url); //http post request
		httpPost.setEntity(new StringEntity(entityString));  // for adding payload
		
		// add headers
		for(Map.Entry<String, String> entry:hm.entrySet()) {
			httpPost.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeablehttpresponse=httpClient.execute(httpPost);
	    return closeablehttpresponse;
		
	}
	
	
	
}