package com.thecommerce.exception;

public class ExistedByNickNameException extends Exception{
	private Object data;
	public ExistedByNickNameException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
