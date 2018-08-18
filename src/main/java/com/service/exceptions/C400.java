package com.service.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.exception.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@RestControllerAdvice
public class C400 {
	private static int count = 0;
	
	@ExceptionHandler(value = {E400.class, UnrecognizedPropertyException.class, HttpMessageNotReadableException.class, ConstraintViolationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMsg defaultErrorHandler(HttpServletRequest req, HttpServletResponse response, Exception e) throws Exception {
	    // Otherwise setup and send the user to a default error-view.
		ErrorMsg msg = new ErrorMsg();
		msg.setHeader(HttpStatus.BAD_REQUEST.name());
	    msg.setCode("E-400-"+count++);
	    String x = e.getMessage();
	    int i = x.indexOf(':');
	    if(i > 0){
	    	msg.setMessage(e.getMessage().substring(0, i));
	    } else {
	    	msg.setMessage(e.getMessage());
	    }
	    msg.setUrl(req.getRequestURL().toString());
	    msg.setDate(LocalDateTime.now().toString());
	    return msg;
	}
	
}