package com.thecommerce.exception;

public class ExistedByEmailException extends Exception{
	private Object data;
	public ExistedByEmailException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
