package com.mage.param.req;

import com.mage.common.BaseNParam;
import com.mage.param.resp.ZteResponse;

public abstract class ZteRequest<T extends ZteResponse> extends BaseNParam {

	private static final long serialVersionUID = 8461944730228166458L;

	String remote_type ="REMOTE";					//LOCAL,REMOTE 缺省为远程调用
	
	private String inf_log_id;						//接口日志id
	
}