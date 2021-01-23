package com.qa.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClientExample {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
		HttpGet httpGet=new HttpGet("http://parabank.parasoft.com/parabank/services/bank/customers/12212/");
		CloseableHttpResponse closeableHttpResponse=closeableHttpClient.execute(httpGet);
		System.out.println("Status Code is: "+closeableHttpResponse.getStatusLine().getStatusCode());
		/*Scanner  sc=new Scanner(closeableHttpResponse.getEntity().getContent());
		
		 * while(sc.hasNext()) { System.out.println(sc.nextLine()); }
		 */
		
		 Header firstHeader=closeableHttpResponse.getFirstHeader("Content-Type");
		 System.out.println("first header value is: "+firstHeader.getValue());
		 Header headers[]=closeableHttpResponse.getAllHeaders();
		 HashMap <String,String> hm=new HashMap<String,String>();
		 for(Header header:headers) {
			 hm.put(header.getName(), header.getValue());
		 }
		 System.out.println("Headers are: "+hm);
			 
		 
	}

}
