package com.poc.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class OpenAiApiClient {

	public enum OpenAiService {
	 GPT_3;
	}

	@Value("${openai_api_key}")
	private String openaiApiKey;

	public HttpClient client = HttpClient.newHttpClient();

	public String postToOpenAiApi(String requestBodyAsJson, OpenAiService service)
			throws IOException, InterruptedException {
		
        //sets the Content-Type header to application/json using HttpHeaders.CONTENT_TYPE.
		var request = HttpRequest.newBuilder().uri(selectUri(service))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				
				//sets the Authorization header with the value "Bearer " followed by 
				//the openaiApiKey variable.
				//This assumes that openaiApiKey is a valid API key for authentication.
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
				.POST(BodyPublishers.ofString(requestBodyAsJson)).build();
		
		//returns the response body as a string.
		return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
	}

	private URI selectUri(OpenAiService service) {
		return URI.create(switch (service) {
		case GPT_3 -> "https://api.openai.com/v1/completions";
		
		});
	}

}
