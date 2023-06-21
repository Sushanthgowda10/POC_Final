package com.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.poc.service.OpenAiApiClient;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.*;
@SpringBootTest
public class OpenAiApiClientTest {

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse<String> httpResponse;

    private OpenAiApiClient openAiApiClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        openAiApiClient = new OpenAiApiClient();
        openAiApiClient.client = httpClient;
    }

    @Test
    void testPostToOpenAiApi() throws IOException, InterruptedException {
        // Given
        String requestBodyAsJson = "{\"text\": \"Hello, World!\"}";
        String expectedResponse = "Response from OpenAI API";

        OpenAiApiClient.OpenAiService service = OpenAiApiClient.OpenAiService.GPT_3;

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(expectedResponse);

        // When
        String response = openAiApiClient.postToOpenAiApi(requestBodyAsJson, service);

        // Then
        verify(httpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        Assertions.assertEquals(expectedResponse, response);
    }
}
