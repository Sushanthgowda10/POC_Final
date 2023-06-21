package com.junit.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.poc.model.request.ChatGptRequest;
import com.poc.service.OpenAiApiClient;
import com.poc.service.OpenAiApiClient.OpenAiService;
import com.poc.service.POCServiceImpl;

@SpringBootTest
public class SpringBootChatGptTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mock;

	@Before
	public void setup() {
		mock = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testEmployee() throws Exception {
//		mockMvc.perform(get("/response")).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
//				.andExpect(jsonPath("$.name").value("emp1")).andExpect(jsonPath("$.designation").value("manager"))
//				.andExpect(jsonPath("$.empId").value("1")).andExpect(jsonPath("$.salary").value(3000));
		// Create a mock ChatGptRequest object
		ChatGptRequest chatRequest = mock(ChatGptRequest.class);

		// Create an instance of YourClass
		POCServiceImpl yourClass = new POCServiceImpl();

		// Call the buildHttpEntity method
		HttpEntity<ChatGptRequest> httpEntity = yourClass.buildHttpEntity(chatRequest);

		// Verify the MediaType is set to JSON
		assertEquals(MediaType.APPLICATION_JSON, httpEntity.getHeaders().getContentType());

		// Verify the request object is set correctly
		assertEquals(chatRequest, httpEntity.getBody());
	}
	
	@Test
	public void testPostToOpenAiApi() throws IOException, InterruptedException {
	    // Create a mock OpenAiService instance
	    OpenAiService mockService = Mockito.mock(OpenAiService.class);

	    // Set up the expected request body and response
	    String requestBodyAsJson = "{\"text\": \"Hello, OpenAI!\"}";
	    String expectedResponse = "{\"result\": \"Hello from OpenAI!\"}";

	    // Configure the mock service to return the expected response
	    when(mockService.sendRequest(requestBodyAsJson)).thenReturn(expectedResponse);

	    // Create an instance of the class under test
	    OpenAiApiClient yourClass = new OpenAiApiClient();

	    // Call the method being tested
	    String actualResponse = yourClass.postToOpenAiApi(requestBodyAsJson, mockService);

	    // Verify that the mock service was called with the expected request body
	    verify(mockService).sendRequest(requestBodyAsJson);

	    // Verify that the actual response matches the expected response
	    assertEquals(expectedResponse, actualResponse);
	}


}
