package com.mage.common;

/**
 * 
 * @author pzh
 */
public class MageError {
	
	String type_code;  				//BUSS业务异常
	String error_code;				//错误编码
	String error_msg;				//错误信息
	String error_cause;				//错误原因
	
	public MageError(String error_code,String error_msg){
		this.error_code =error_code;
		this.error_msg =error_msg;
	}
	
	public MageError(String error_code,String error_msg,String error_cause){
		this.error_code =error_code;
		this.error_msg =error_msg;
		this.error_cause =error_cause;
	}
	
	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public String getError_cause() {
		return error_cause;
	}

	public void setError_cause(String error_cause) {
		this.error_cause = error_cause;
	}
}
