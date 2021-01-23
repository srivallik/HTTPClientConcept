package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.users.data.Users;

public class PostAPITestCase {
	
	String url="https://reqres.in/";
	String apiUrl;
	RestClient restclient;
	
	@BeforeMethod
	public void setUp() {
		apiUrl=url+"/api/users";
	}
	
	@Test
	public void CreateUserTestCase() throws ClientProtocolException, IOException {
		
		restclient =new RestClient();
		
		//header
		
		HashMap<String,String>hm=new HashMap<String,String>();
		hm.put("Content-Type", "application/json");
		
		// prepare json payload:jackson core and databind api's
		
		ObjectMapper mapper=new ObjectMapper();
		Users users=new Users("naveen","leader");
		
		// convert java object to json string: Serialization -- Marshelling
		
		String usersJsonString=mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		CloseableHttpResponse response=restclient.post(apiUrl, usersJsonString, hm);
		
		int statusCode=response.getStatusLine().getStatusCode();
		System.out.println("Status Code is: "+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		// get the json payload response
		/*
		 * HttpEntity httpEntity=reponse.getEntity(); String
		 * responseString=EntityUtils.toString(httpEntity);
		 * System.out.println("response string is: "+responseString);
		 */
		
		String responseStrings=EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject responsejson=new JSONObject(responseStrings);
		
		System.out.println("the json response is: "+responsejson);
		
		// convert json string to object :: deserialization -- un marshelling
		
		Users usersobj=mapper.readValue(responseStrings, Users.class);
		System.out.println("get Name: "+usersobj.getName());
		System.out.println("get Job: "+usersobj.getJob());
		
		Assert.assertEquals(usersobj.getName(), users.getName());
		Assert.assertEquals(usersobj.getJob(), users.getJob());
		
		
		
	}
	

}
