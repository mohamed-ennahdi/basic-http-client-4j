package com.github.mohamedennahdi.basichttpclient4j.client;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.methods.CloseableHttpResponse;

public interface IBasicHTTTPClient {
	public CloseableHttpResponse doGet() throws IOException, URISyntaxException;
	public CloseableHttpResponse doPost() throws IOException, URISyntaxException;
	public CloseableHttpResponse doPut() throws IOException, URISyntaxException;
}
