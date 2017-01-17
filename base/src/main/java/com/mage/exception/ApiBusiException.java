package com.mage.exception;

import com.mage.consts.ApiConsts;


/**
 * API前置检查异常。
 * 
 * @author pzh
 * @date 2016年10月8日 下午1:59:36
 */
public class ApiBusiException extends Exception {

	private static final long serialVersionUID = -7787145910600194272L;

	public ApiBusiException(String errMsg) {
		super(ApiConsts.EXP_BUSS + ":" + "ApiBusiException"+":"+errMsg);
	}

}
