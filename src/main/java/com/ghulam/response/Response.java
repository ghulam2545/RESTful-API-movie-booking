package com.ghulam.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Each response will be based on
 * 1. response message
 * 2. http status code
 * 3. response data
 */
public class Response {
    public static ResponseEntity<Object> handler(String message, HttpStatus status, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", status);
        response.put("data", data);

        return new ResponseEntity<>(response, status);
    }
}
