package com.github.mohamedennahdi.basichttpclient4j.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class BasicHTTTPClient implements IBasicHTTTPClient {
	protected String url;
	protected Map<String, String> headers;
	protected Map<String, String> params;
	protected String jsonBody;
	protected Map<String, String> body;
	private final CloseableHttpClient client = HttpClients.createDefault();

	public BasicHTTTPClient(final Builder builder) {
		this.url = builder.url;
		this.headers = builder.headers;
		this.params = builder.params;
		this.body = builder.body;
		this.jsonBody = builder.jsonBody;
	}

	public String getUrl() {
		return url;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public CloseableHttpClient getClient() {
		return client;
	}

	public String getJsonBody() {
		return jsonBody;
	}

	public Map<String, String> getBody() {
		return body;
	}

	public void close() throws IOException {
		client.close();
	}

	public static class Builder {
		private String url;
		private Map<String, String> headers;
		private Map<String, String> params;
		private Map<String, String> body;
		private String jsonBody;

		public String getUrl() {
			return url;
		}

		public Builder setUrl(final String url) {
			this.url = url;
			return this;
		}

		public Builder setHeaders(final Map<String, String> headers) {
			this.headers = headers;
			return this;
		}

		public Builder setParams(final Map<String, String> params) {
			this.params = params;
			return this;
		}

		public Builder setBody(final String body) {
			this.jsonBody= body;
			return this;
		}

		public Builder setBody(final Map<String, String> body) {
			this.body = body;
			return this;
		}

		public BasicHTTTPClient build() {
			return new BasicHTTTPClient(this);
		}

	}

	@Override
	public CloseableHttpResponse doPost() throws ClientProtocolException, IOException, URISyntaxException {
		HttpPost request = new HttpPost(getUrl());
		request = (HttpPost) updateRequest(request);

		return getClient().execute(request);
	}

	@Override
	public CloseableHttpResponse doGet() throws IOException, URISyntaxException {
		HttpGet request = new HttpGet(getUrl());
		request = (HttpGet) updateRequest(request);

		return getClient().execute(request);
	}

	private HttpRequestBase updateRequest(HttpRequestBase request) throws URISyntaxException, UnsupportedEncodingException {
		if (Objects.nonNull(headers)) {
			headers.forEach(request::addHeader);
		}
		URIBuilder builder = new URIBuilder(request.getURI());
		if (Objects.nonNull(params)) {
			params.forEach(builder::addParameter);
		}
		if (request instanceof HttpEntityEnclosingRequestBase baseRequest) {
			if (Objects.nonNull(jsonBody) && !jsonBody.isBlank()) {
				baseRequest.setEntity(new StringEntity(this.jsonBody));
			}
		}

		request.setURI(builder.build());
		return request;
	}

	@Override
	public CloseableHttpResponse doPut() throws IOException, URISyntaxException {
		HttpPut request = new HttpPut(getUrl());
		request = (HttpPut) updateRequest(request);
		
		return this.client.execute(request);
	}
}