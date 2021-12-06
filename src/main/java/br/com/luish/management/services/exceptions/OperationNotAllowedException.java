package br.com.luish.management.services.exceptions;

public class OperationNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OperationNotAllowedException(String msg) {
		super(msg);
	}

	public OperationNotAllowedException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
