package com.mage.cms.common.params.base.resp;

import java.io.Serializable;

/**
 * 模板添加出参对象
 * 
 */
public class CmsLineResp  implements Serializable {
	private boolean success =true;
	private String error;
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	
}
