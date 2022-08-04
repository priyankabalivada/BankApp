package com.example.demo.Error;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.Exception.NotFoundException;
import com.example.demo.Exception.AccountAlreadyexistException;
import com.example.demo.Exception.BalanceExceedLimitException;
import com.example.demo.Exception.SameAccountexception;
import com.example.demo.Exception.NegativeBalanceException;
    @RestControllerAdvice
    public class ExceptionAdvice{
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomErrorResponse>handleEmployeeNotFoundException (NotFoundException e) { 
     CustomErrorResponse error=new CustomErrorResponse ("NOT_FOUND_EXCEPTION", e.getMessage());
     error.setTimestamp (LocalDateTime.now());
     error.setStatus (HttpStatus.NOT_FOUND.toString()); 
     return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.NOT_FOUND);
       }
	
	@ExceptionHandler(BalanceExceedLimitException.class)
	 public ResponseEntity<CustomErrorResponse>BalanceExceedLimitException (BalanceExceedLimitException e) { 
		 CustomErrorResponse error=new CustomErrorResponse ("BAD_REQUEST", e.getMessage());
		 error.setTimestamp (LocalDateTime.now());
		 error.setStatus (HttpStatus.BAD_REQUEST.toString()); 
		 return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.BAD_REQUEST);
		 }
	
	@ExceptionHandler(NegativeBalanceException.class)
	 public ResponseEntity<CustomErrorResponse>NegativeBalanceException (NegativeBalanceException e) { 
		 CustomErrorResponse error=new CustomErrorResponse ("BAD_REQUEST", e.getMessage());
		 error.setTimestamp (LocalDateTime.now());
		 error.setStatus (HttpStatus.BAD_REQUEST.toString()); 
		 return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.BAD_REQUEST);
		 }
	 
	 
	 
	@ExceptionHandler(AccountAlreadyexistException .class)
	 public ResponseEntity<CustomErrorResponse>AccountAlreadyexistException (AccountAlreadyexistException e) { 
		 CustomErrorResponse error=new CustomErrorResponse ("BAD_REQUEST", e.getMessage());
		 error.setTimestamp (LocalDateTime.now());
		 error.setStatus (HttpStatus.BAD_REQUEST.toString()); 
		 return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.BAD_REQUEST);
		 }
	 
	 
	 
	 
	@ExceptionHandler( SameAccountexception .class)
	 public ResponseEntity<CustomErrorResponse> SameAccountexception  (SameAccountexception  e) { 
		 CustomErrorResponse error=new CustomErrorResponse ("BAD_REQUEST", e.getMessage());
		 error.setTimestamp (LocalDateTime.now());
		 error.setStatus (HttpStatus.BAD_REQUEST.toString()); 
		 return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.BAD_REQUEST);
		 }
	 

}

