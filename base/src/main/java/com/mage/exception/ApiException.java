package com.mage.exception;

/**
 * TAOBAO客户端异常。
 * 
 * @author pzh
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -238091758285157331L;

	private String errCode;
	private String errMsg;

	public ApiException() {
		super();
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiException(String message) {
		super(message);
	}

	public ApiException(Throwable cause) {
		super(cause);
	}

	public ApiException(String errCode, String errMsg) {
		super(errCode + ":" +"ApiException"+":"+errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

}
