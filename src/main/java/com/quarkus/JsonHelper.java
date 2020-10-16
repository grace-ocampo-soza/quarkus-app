package com.quarkus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

    public static Customer parse(String c) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(c, Customer.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(Customer customer) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
