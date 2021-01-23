package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;

public class GetAPITestCase {
	
	String url="http://restcountries.eu/rest/v1/name";
	String apiUrl;
	RestClient restClient;
	
	@BeforeMethod
	public void setUp() {
		apiUrl=url+"/Austria";
		
	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient=new RestClient();
		CloseableHttpResponse closeableHttpResponse=restClient.get(apiUrl);
		
		// 1. status code
		int statusCode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The status code is: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// 2. json string
		
		HttpEntity httpEntity=closeableHttpResponse.getEntity();
		String responseString=EntityUtils.toString(httpEntity);
		System.out.println("response string is: "+responseString);
		
		// 3. headers
		
		Header headersArray[]= closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders=new HashMap<String,String>();
		for(Header header:headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers are:"+allHeaders);
		System.out.println(allHeaders.get("Date"));
		String contentType=allHeaders.get("Content-Type");
		Assert.assertEquals(contentType, "application/json;charset=utf-8");
		
	}
}
