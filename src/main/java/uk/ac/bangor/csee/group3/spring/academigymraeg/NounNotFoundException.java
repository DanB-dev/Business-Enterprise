package uk.ac.bangor.csee.group3.spring.academigymraeg;

import org.springframework.security.core.AuthenticationException;

public class NounNotFoundException extends AuthenticationException{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NounNotFoundException(String msg) {
		super(msg);
	}
	
	public NounNotFoundException(String msg,Throwable cause) {
		super(msg,cause);
	}
}
