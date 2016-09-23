/**
 *
 * 日    期：12-2-10
 */
package com.mage.param.req;

import java.io.Serializable;

import com.mage.context.RopRequestContext;
import com.mage.database.NotDbField;

/**
 * ROP服务的请求对象，
 * 请求方法的入参必须是继承于该类。
 *
 * @author pzh
 * @version 1.0
 */
public interface RopRequest extends Serializable{

	@NotDbField
	public String getSessionId();
	
	@NotDbField
	public void setSessionId(String sessionId);
	
	@NotDbField
	public String getUserSessionId();
	
	@NotDbField
	public void setUserSessionId(String userSessionId);
	
    /**
     * 获取服务方法的上下文
     *
     * @return
     */
	@NotDbField
    RopRequestContext getRopRequestContext();

}

