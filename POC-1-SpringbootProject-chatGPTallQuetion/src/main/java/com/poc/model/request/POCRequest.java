package com.poc.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
/**
 * Model class to request a question to Chatgpt
 * @author vikas.h
 *
 */
public class POCRequest implements Serializable {
    private String message;
}



