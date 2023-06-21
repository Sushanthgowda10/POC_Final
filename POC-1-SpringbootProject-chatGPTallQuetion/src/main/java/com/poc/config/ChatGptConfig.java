package com.poc.config;

public class ChatGptConfig {

	//properties to configure openAI API to springboot project
    public static final String AUTHORIZATION = "Authorization";
    //type of token
    public static final String BEARER = "Bearer ";
    //Secret API key generated in openAI account
    public static final String API_KEY = "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";
    
    //It represents the specific model or engine used for generating text-based responses in the OpenAI API.
    public static final String MODEL = "text-davinci-003";
    
    //represents the maximum number of tokens allowed in the generated text output. 
    public static final Integer MAX_TOKEN = 300;
    
    //A lower value like 0.0 produces more focused and deterministic responses.
    public static final Double TEMPERATURE = 0.0;
    
    // It represents the probability threshold for generating text.
    public static final Double TOP_P = 1.0;
    
    //It specifies the media type or content type of the API request payload.
    public static final String MEDIA_TYPE = "application/json; charset=UTF-8";
    
    //URL that the API requests will be sent to.
    public static final String URL = "https://api.openai.com/v1/completions";
}
