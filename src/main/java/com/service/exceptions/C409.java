package com.service.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class C409 {
	private static int count = 0;
	
	@ExceptionHandler(value = E409.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorMsg defaultErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
	    // Otherwise setup and send the user to a default error-view.
		ErrorMsg msg = new ErrorMsg();
		msg.setHeader(HttpStatus.CONFLICT.name());
	    msg.setCode("E-409-"+count++);
	    msg.setMessage(e.getMessage());
	    msg.setUrl(req.getRequestURL().toString());
	    msg.setDate(LocalDateTime.now().toString());
	    return msg;
	}
	
}