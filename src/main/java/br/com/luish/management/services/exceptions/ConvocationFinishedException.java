package br.com.luish.management.services.exceptions;

public class ConvocationFinishedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConvocationFinishedException(String msg) {
		super(msg);
	}
	
	public ConvocationFinishedException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
