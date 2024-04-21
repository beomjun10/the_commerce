package com.thecommerce.exception;

public class ExistedByPhoneNumberException extends Exception{
	private Object data;
	public ExistedByPhoneNumberException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
