package com.poc.service;

import com.poc.model.request.POCRequest;
import com.poc.model.response.ChatGptResponse;

public interface POCService {

    ChatGptResponse askQuestion(POCRequest botRequest);
}
