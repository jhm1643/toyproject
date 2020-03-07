package net.class101.server1;

public class SoldOutException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9107340850676129312L;
	public SoldOutException() {
		super();
	}
	public SoldOutException(String message) {
		super(message);
	}
}
