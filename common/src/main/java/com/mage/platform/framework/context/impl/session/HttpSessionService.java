package com.mage.platform.framework.context.impl.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.mage.platform.framework.context.ThreadContextHolder;

/**
 * web Session 实现类
 * @author pzh
 * @date 2016年6月15日 下午4:16:47
 */
@Service
public class HttpSessionService implements ISessionService {

	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public Object getAttribute(String arg0) {
		HttpSession session = this.getSession();
		return session == null ? null : session.getAttribute(arg0);
	}

	@Override
	public String getId() {
		HttpSession session = this.getSession();
		return session == null ? null :  session.getId();
	}

	@Override
	public void invalidate() {
		HttpSession session = this.getSession();
		if(session != null)
			session.invalidate();
	}

	@Override
	public void removeAttribute(String arg0) {
		HttpSession session = this.getSession();
		if(session != null)
			session.removeAttribute(arg0);
	}

	@Override
	public void setAttribute(String arg0, Object arg1) {
		HttpSession session = this.getSession();
		if (session != null) {
			session.setAttribute(arg0, arg1);
		}
	}
	
	/**
	 * 获取session
	 * @return
	 */
	private HttpSession getSession(){
		HttpServletRequest request = ThreadContextHolder.getHttpRequest();
		if(request == null || request.getSession() == null)
			logger.info("============================>>>sessoin 失效");
		return request.getSession();
	}
}
