package com.cos.blog.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice //모든 exception 발생시 실행
@RestController 
public class GlobalExceptionHandler {

	
	@ExceptionHandler (value = IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e){		
		return "<h1>" + e.getMessage() + "</h1>";		
	}

	@ExceptionHandler (value = EmptyResultDataAccessException.class)
	public String handleArgumentException(EmptyResultDataAccessException e){		
		System.out.println("개별");
		return "<h1>" + e.getMessage() + "</h1>";		
	}
	
	
	@ExceptionHandler (value = Exception.class)
	public String handleArgumentException(Exception e){		
		System.out.println("all");
		return "<h1>" + e.getMessage() + "</h1>";		
	}
	
	
}
