package com.spring.login.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseJson {

	Map<String, Object> map = new HashMap<String, Object>();

	public ResponseEntity<Map<String, Object>> messageOK(String message, Object data) {
		map.put("status", "Ok");		
		map.put("data", data);		
		map.put("message", message);		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
}
