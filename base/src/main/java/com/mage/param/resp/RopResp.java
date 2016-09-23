/**
 *
 * 日    期：12-2-10
 */
package com.mage.param.resp;

import java.io.Serializable;

import com.mage.database.NotDbField;

/**
 * ROP服务的返回对象，
 * 返回方法的入参必须是继承于该类。
 * @author pzh
 * @version 1.0
 */
public abstract class RopResp implements Serializable {

	public String error_code ="-1";
	public String error_msg;
	
	@NotDbField
	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	@NotDbField
	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}


}
