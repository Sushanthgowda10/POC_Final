package com.poc.model.response;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
/**
 * model class for ChatGptResponse
 *
 */
public class ChatGptResponse implements Serializable {
    private String id;
    private String object;
    private String model;
    private LocalDate created;
    private List<Choice> choices;
    
    public Optional<String> firstAnswer() {
		if (choices == null || choices.isEmpty())
			return Optional.empty();
		return Optional.of(choices.get(0).text);
	}
}




