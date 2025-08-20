package com.github.mohamedennahdi.basichttpclient4j.client;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Generated(value = "org.junit-tools-1.1.0")
public class BasicHTTPClientFakeAPITest {

	public static final Logger LOGGER = LoggerFactory.getLogger(BasicHTTPClientFakeAPITest.class);
	String url = "https://fakerestapi.azurewebsites.net/api/v1/Activities";

	@Test
	public void doGetTest() throws Exception {
		BasicHTTTPClient testSubject;
		CloseableHttpResponse actual;

		LOGGER.info("Calling {}", this.url);

		// default test
		testSubject = new BasicHTTTPClient.Builder().setUrl(url).build();
		actual = testSubject.doGet();

		assertEquals(actual.getStatusLine().getStatusCode(), 200);
		/*
		 *
		 */
		String json = EntityUtils.toString(actual.getEntity());
		JsonArray jsonObject = new Gson().fromJson(json, JsonArray.class);
		assertTrue(jsonObject.size() > 0);

		LOGGER.info("Calling {}", url + "/1");

		// default test
		testSubject = new BasicHTTTPClient.Builder().setUrl(url + "/1").build();
		actual = testSubject.doGet();

		assertEquals(actual.getStatusLine().getStatusCode(), 200);
		/*
		 *
		 */
		String expected = """
						{
						  "id": 1,
						  "title": "Activity 1",
						  "dueDate": "2025-08-15T16:04:45.6842342+00:00",
						  "completed": false
						}
				""";
		json = EntityUtils.toString(actual.getEntity());
		JsonObject actualJSON = new Gson().fromJson(json, JsonObject.class);
		JsonObject expectedJSON = new Gson().fromJson(expected, JsonObject.class);
		actualJSON.remove("dueDate");
		expectedJSON.remove("dueDate");

		assertEquals(actualJSON, expectedJSON);

	}

	@Test
	public void doPostTest() throws Exception {
		BasicHTTTPClient testSubject;
		CloseableHttpResponse actual;

		String body = """
					{
					  "id": 77,
					  "title": "Activity 77",
					  "dueDate": "2025-08-06T14:17:11.218Z",
					  "completed": false
					}
				""";

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "text/plain");

		testSubject = new BasicHTTTPClient.Builder().setUrl(url).setHeaders(headers).setBody(body).build();

		actual = testSubject.doPost();

		assertEquals(actual.getStatusLine().getStatusCode(), 200);
	}

	@Test
	public void doPutTest() throws Exception {
		String body, expected; 
		body = expected = """
				{
				  "id": 30,
				  "title": "Altered Activity 30",
				  "dueDate": "2025-08-20T03:16:56.4199481+00:00",
				  "completed": true
				}
					""";

		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Accept", "text/plain");

		BasicHTTTPClient testSubject = new BasicHTTTPClient.Builder().setUrl(url + "/30").setHeaders(headers).setBody(body)
				.build();

		CloseableHttpResponse actual = testSubject.doPut();

		assertEquals(actual.getStatusLine().getStatusCode(), 200);
		
		String json = EntityUtils.toString(actual.getEntity());
		JsonObject actualJSON = new Gson().fromJson(json, JsonObject.class);
		JsonObject expectedJSON = new Gson().fromJson(expected, JsonObject.class);
		
		assertEquals(actualJSON, expectedJSON);
	}
}