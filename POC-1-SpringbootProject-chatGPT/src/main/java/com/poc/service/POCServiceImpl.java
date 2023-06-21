//package com.poc.service;
//
//import com.poc.config.ChatGptConfig;
//import com.poc.model.request.POCRequest;
//import com.poc.model.request.ChatGptRequest;
//import com.poc.model.response.ChatGptResponse;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
///**
// * service class to interact with openAI Api and to genarte response
// *
// */
//public class POCServiceImpl implements POCService {
//
//	//creating restTemplate
//    private static RestTemplate restTemplate = new RestTemplate();
//
//    //    Build headers
//    public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
//        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
//        return new HttpEntity<>(chatRequest, headers);
//    }
//
//    //    Generate response from 
//    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatRequestHttpEntity) {
//        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
//                ChatGptConfig.URL,
//                chatRequestHttpEntity,
//                ChatGptResponse.class);
//
//        return responseEntity.getBody();
//    }
//
//    //configure the chatGPT properties
//    public ChatGptResponse askQuestion(POCRequest pocRequest) {
//        return this.getResponse(
//                this.buildHttpEntity(
//                        new ChatGptRequest(
//                                ChatGptConfig.MODEL,
//                                pocRequest.getMessage(),
//                                ChatGptConfig.TEMPERATURE,
//                                ChatGptConfig.MAX_TOKEN,
//                                ChatGptConfig.TOP_P)));
//    }
//}
//
//
//
//
//
//
