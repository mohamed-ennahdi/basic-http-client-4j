package com.github.mohamedennahdi.basichttpclient4j.client;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;

public interface IBasicHTTTPClient {
	public CloseableHttpResponse doPost() throws ClientProtocolException, IOException, URISyntaxException;
	public CloseableHttpResponse doGet() throws ClientProtocolException, IOException, URISyntaxException;
}
