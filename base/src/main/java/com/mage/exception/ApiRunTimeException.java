package com.mage.exception;

/**
 * API前置检查异常。
 * 
 * @author pzh
 */
public class ApiRunTimeException extends ApiException {

	private static final long serialVersionUID = -7787145910600194272L;

	public ApiRunTimeException(String errCode, String errMsg) {
		super(errCode, errMsg);
	}

}
