package com.service.exceptions;

public abstract class E extends RuntimeException{
	
	public E(String message){
		this.message = message;
	}
	
	private String message;
	
	public String getMessage(){
		return message;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}