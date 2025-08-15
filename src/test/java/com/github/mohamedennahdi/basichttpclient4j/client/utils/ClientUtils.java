package com.github.mohamedennahdi.basichttpclient4j.client.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.mohamedennahdi.basichttpclient4j.app.BasicHTTPClient4JApp;

public class ClientUtils {
	public static String readResource(String resource) throws URISyntaxException, IOException {
		Path path = Paths.get(BasicHTTPClient4JApp.class.getResource(resource).toURI());
		return new String(Files.readAllBytes(path));
	}
}
