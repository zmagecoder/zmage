package com.mage.cms.common.parser;

import com.mage.cms.common.model.Modual;

/**
 * @author pzh
 * 静态模式参数解析构造器
 */
public  abstract class CmsStaticLayOutParamPraser{
	public CmsStaticLayOutParamPraser(){}
	public abstract String genStcStoreLayOutParam(Modual modual);
}
