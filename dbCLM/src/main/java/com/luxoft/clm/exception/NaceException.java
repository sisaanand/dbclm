package com.luxoft.clm.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NaceException extends Exception{

	    private static final long serialVersionUID = 1L;

	    public NaceException(String message){
	        super(message);
	    }
	}


