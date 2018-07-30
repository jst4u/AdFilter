package com.loit.core.exception;

public class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
		
	public String msg = "" ;
	
	public AppException(String message, Throwable cause) {
        super(message,cause);
        setMsg(message) ;
    } 
	
	public AppException(String message) {
        super(message);
        setMsg(message) ;
    } 
	
	public AppException(Throwable cause) {
        super(cause);
    } 
	
	public AppException() {
		super() ;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
