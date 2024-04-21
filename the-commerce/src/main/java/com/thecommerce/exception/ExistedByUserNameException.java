package com.thecommerce.exception;

public class ExistedByUserNameException extends Exception{
	private Object data;
	public ExistedByUserNameException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
