package com.mage.cms.common.parser;

/**
 * 
 * @author wu.i
 * 页面执行器
 *
 */
public class CmsPluginTypeFactory {
	private static final CmsPluginTypeFactory inst;
	static{
		inst = new CmsPluginTypeFactory(); 
	}
	public static CmsPluginTypeFactory getInstance(){
		return inst;
	}
	public CmsTplPluginTypePraser getPluginTypeParser(String uri) {
			return new CmsTplPluginTypePraser();
		
	}
}
