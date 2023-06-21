package com.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.helper.CompletionRequest;
import com.poc.helper.FormInputDTO;
import com.poc.model.request.POCRequest;
import com.poc.model.response.ChatGptResponse;
import com.poc.service.POCService;
import com.poc.service.OpenAiApiClient;
import com.poc.service.OpenAiApiClient.OpenAiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/poc")
@RequiredArgsConstructor
/**
 * 
 * @author vikas.h
 *
 */
public class POCController {
	
	private static final String MAIN_PAGE = "index";

    public POCController() {
		this.botService = null;
	}

	
	/**
	 * This API used to dispaly a home page
	 * @return LVN
	 */
	@GetMapping("/")
	public String index() {
		return MAIN_PAGE;
	}
	
	/**
	 * This API is used generate the response and display through html page
	 * 
	 * @param Model model,
	 * @param  @ModelAttribute BotRequest dto
	 * @return LVN
	 */
	@PostMapping("/response")
	public ModelAndView chat(Model model, @ModelAttribute POCRequest dto) {
		System.out.println("Hi");
		ModelAndView modelAndView = new ModelAndView("index");

		try {
			model.addAttribute("request", dto.getMessage());
			System.out.println(dto.getMessage());
			model.addAttribute("response", chatWithGpt3(dto.getMessage()));
		} catch (Exception e) {
			model.addAttribute("response", "Error in communication with OpenAI ChatGPT API.");
		}
		return modelAndView;
	}
	
//  @PostMapping("/response")
//  public String sendMessage(@RequestBody BotRequest botRequest) {
//       botService.askQuestion(botRequest);
//       return "MAIN_PAGE";
//  }
	
	private final POCService botService;

	
	    //used for converting Java objects to JSON
		@Autowired private ObjectMapper jsonMapper;
		
		@Autowired private OpenAiApiClient client;
		
		private String chatWithGpt3(String message) throws Exception {
			//encapsulates the parameters or data required to make a completion request to an API or service
			//And passing the message=question to custome meyhod
			var completion = CompletionRequest.defaultWith(message);
			
			//object into a JSON string representation.
			var postBodyJson = jsonMapper.writeValueAsString(completion);
			
			//making a POST request to the OpenAI API using a client object.
			var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiService.GPT_3);
			
			//deserializing the response body received from the OpenAI API into a Java object of type ChatGptResponse.
			var completionResponse = jsonMapper.readValue(responseBody, ChatGptResponse.class);
			
			//returning the first answer from the completionResponse object as a String
			return completionResponse.firstAnswer().orElseThrow();
		}
	
	
	
}



