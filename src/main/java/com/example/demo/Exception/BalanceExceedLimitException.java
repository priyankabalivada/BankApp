package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) 
public class BalanceExceedLimitException extends RuntimeException {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public BalanceExceedLimitException(String message) {



super (message);

}
}