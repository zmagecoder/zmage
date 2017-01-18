package com.mage.cms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.parser.CmsUrlFactory;
import com.mage.cms.common.parser.UrlPraser;
import com.mage.cms.common.service.ICmsLineService;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.framework.utils.FileBaseUtil;

/**
 * cms在线servlet处理器
 * @author pzh
 * 
 */
public class ICmsViewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2010823280264711126L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String jsonStr ="";
		ThreadContextHolder.setHttpRequest(req);
		try {
			init_context(req, resp);
			jsonStr=exec_context(req,resp);
		} catch (Exception e) {
			jsonStr =destory_context(req,resp);
			e.printStackTrace();
		}finally{
			resp.getWriter().write(jsonStr);
		}
	}
	
	/**
	 * init操作
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	public String init_context(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		ICmsLineService cmsService = CmsServiceFactory.getCmsLineService();
		String jsonReq = FileBaseUtil.readStreamToString(req.getInputStream());
		cmsService.getCmsContext().setJsonReq(jsonReq);
		return jsonReq;
		
	}
	/**
	 * 内容执行
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String exec_context(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		String uri = req.getRequestURI();
		uri = uri.substring(uri.indexOf("icmsview")+"icmsview".length());
		//页面执行处理器
		UrlPraser praser = CmsUrlFactory.getInstance().getUrlParser(uri);
		return praser.perform(req,resp,uri);
	}
	
	public String destory_context(HttpServletRequest req, HttpServletResponse resp){
		//TODO
		String jsonStr="{\"success\":\"1\",\"error\",\"" + "暂未处理错误信息" + "\"}";
		return jsonStr;
	}
}
