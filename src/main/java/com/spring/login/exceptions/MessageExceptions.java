package com.spring.login.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MessageExceptions extends ResponseEntityExceptionHandler {
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	@ExceptionHandler(value = {RuntimeException.class})
	protected ResponseEntity<Object> messageExceptions(HandlerExceptions exception, WebRequest webRequest) {
		
		map.put("message", exception.getMessage());
		map.put("httpstatus", exception.getHttpStatus());
//		map.put("code", exception.getCode());
		return handleExceptionInternal(exception, map, null, exception.getHttpStatus(), webRequest);
			
	}
}
