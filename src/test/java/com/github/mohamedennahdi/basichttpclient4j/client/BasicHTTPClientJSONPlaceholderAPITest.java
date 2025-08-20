package com.github.mohamedennahdi.basichttpclient4j.client;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mohamedennahdi.basichttpclient4j.client.utils.ClientUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Generated(value = "org.junit-tools-1.1.0")
public class BasicHTTPClientJSONPlaceholderAPITest {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(BasicHTTPClientJSONPlaceholderAPITest.class);
	String url = "https://jsonplaceholder.typicode.com/posts";
	
	@Test
	public void doGetTest() throws Exception {
		BasicHTTTPClient testSubject;
		CloseableHttpResponse actual;
		
		LOGGER.info("Calling {}", this.url);

		// default test
		testSubject = new BasicHTTTPClient.Builder()
				.setUrl(url)
				.build();
		actual = testSubject.doGet();

		assertEquals(actual.getStatusLine().getStatusCode(), 200);
		/*
		 *
		 */
		String json = EntityUtils.toString(actual.getEntity());
		JsonArray jsonObject = new Gson().fromJson(json, JsonArray.class);
		assertTrue(jsonObject.size() > 0);
		String productId = jsonObject.get(0).getAsJsonObject().get("id").getAsString();
		LOGGER.info("Calling {}", this.url + "/" + productId);

		// default test
		testSubject = new BasicHTTTPClient.Builder()
				.setUrl(this.url + "/" + productId)
				.build();
		actual = testSubject.doGet();

		assertEquals(actual.getStatusLine().getStatusCode(), 200);
		/*
		 *
		 */
		String expected = ClientUtils.readResource("post1.json");
		json = EntityUtils.toString(actual.getEntity());
		JsonObject actualJSON = new Gson().fromJson(json, JsonObject.class);
		JsonObject expectedJSON = new Gson().fromJson(expected, JsonObject.class);
		assertEquals(expectedJSON, actualJSON);
	}

	@Test
	public void doPostTest() throws Exception {
		String body = ClientUtils.readResource("newpost.json");
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "text/plain");

		BasicHTTTPClient testSubject = new BasicHTTTPClient.Builder()
				.setUrl(this.url)
				.setHeaders(headers)
				.setBody(body)
				.build();

		CloseableHttpResponse actual = testSubject.doPost();

		assertEquals(201, actual.getStatusLine().getStatusCode());
	}
	

	@Test
	public void doPutTest() throws Exception {
		String expected = ClientUtils.readResource("altered-post2.json");
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "text/plain");

		BasicHTTTPClient testSubject = new BasicHTTTPClient.Builder()
				.setUrl(this.url + "/2")
				.setHeaders(headers)
				.setBody(expected)
				.build();

		CloseableHttpResponse actual = testSubject.doPut();

		assertEquals(actual.getStatusLine().getStatusCode(), 200);
		
		String json = EntityUtils.toString(actual.getEntity());
		JsonObject actualJSON = new Gson().fromJson(json, JsonObject.class);
		JsonObject expectedJSON = new Gson().fromJson(expected, JsonObject.class);
		
		assertEquals(actualJSON, expectedJSON);
	}
}