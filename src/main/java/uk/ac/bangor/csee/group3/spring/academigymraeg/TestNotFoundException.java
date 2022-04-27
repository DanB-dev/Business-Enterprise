package uk.ac.bangor.csee.group3.spring.academigymraeg;

import org.springframework.security.core.AuthenticationException;

public class TestNotFoundException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestNotFoundException(String msg) {
		super(msg);
	}

	public TestNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
